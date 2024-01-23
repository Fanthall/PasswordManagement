package com.fanthal.PasswordManagement.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanthal.PasswordManagement.exceptions.ObjectNotFoundException;
import com.fanthal.PasswordManagement.model.RefreshToken;
import com.fanthal.PasswordManagement.model.User;
import com.fanthal.PasswordManagement.repository.RefreshTokenRepository;
import com.fanthal.PasswordManagement.repository.UserRepository;
import com.fanthal.PasswordManagement.type.ErrorMessage;
import com.fanthal.PasswordManagement.util.RefreshTokenHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RefreshTokenServices {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public RefreshToken findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
		boolean existToken = refreshTokenRepository.existByUserId(userId);
		if (existToken) {
			RefreshToken existRefreshToken = refreshTokenRepository.findByUserId(userId);
			refreshTokenRepository.delete(existRefreshToken);
		}
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUser(user);
		refreshToken.setExpireDate(new Date(RefreshTokenHelper.getCurrentTime() + 604800000));// 7 gün
		refreshToken.setToken(RefreshTokenHelper.getRandomUUID());
		log.info("Refresh Tokeni kayıt edildi.");
		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public boolean verifyExpiration(RefreshToken token) {
		if (token.getExpireDate().compareTo(new Date()) < 0) {
			log.info("Refresh-Token silindi.");
			refreshTokenRepository.delete(token);
			return false;
		}
		token.setExpireDate(new Date(RefreshTokenHelper.getCurrentTime() + 86400000));
		refreshTokenRepository.save(token);
		log.info("Geçerli Refresh-Token.");
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteByUserId(Long userId) {
		RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId);
		if (refreshToken != null) {
			log.info("Refresh-Token iptal edildi.");
			refreshTokenRepository.delete(refreshToken);
		} else {
			throw new ObjectNotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND);
		}
	}
}
