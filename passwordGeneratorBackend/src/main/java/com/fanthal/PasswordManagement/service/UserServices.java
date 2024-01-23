package com.fanthal.PasswordManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanthal.PasswordManagement.dto.JwtResponse;
import com.fanthal.PasswordManagement.dto.PageableResponse;
import com.fanthal.PasswordManagement.exceptions.FnthlException;
import com.fanthal.PasswordManagement.exceptions.ObjectNotFoundException;
import com.fanthal.PasswordManagement.model.RefreshToken;
import com.fanthal.PasswordManagement.model.User;
import com.fanthal.PasswordManagement.repository.UserRepository;
import com.fanthal.PasswordManagement.type.ErrorMessage;
import com.fanthal.PasswordManagement.util.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServices {

	final UserRepository userRepository;
	final PasswordEncoder encoder;
	final RefreshTokenServices refreshTokenService;

	final JwtUtils jwtUtils;

	public User getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
	}

	public JwtResponse getAuthUser(Long id) {

		User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
		String jwt = jwtUtils.generateJwtToken(user.getUsername());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

		return new JwtResponse(jwt, refreshToken.getToken(), user.getId(), user.getUsername());
	}

	public void createUser(User user) {
		boolean existUser = userRepository.checkUserExistwithUsername(user.getUsername());
		if (!existUser) {
			user.setPassword(encoder.encode(user.getPassword()));
			log.info("{} kullanıcısı kayıt oluşturuldu.", user.getUsername());
			user.setActive(true);
			userRepository.save(user);
		} else {
			log.info("{} kullanıcı adında başka biri zaten bulunmakta.", user.getUsername());
			throw new FnthlException(ErrorMessage.USER_EXIST);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateUser(User newUserInfo) {
		boolean existUsername = userRepository.checkExistUsername(newUserInfo.getUsername(), newUserInfo.getId());
		if (!existUsername) {
			User user = userRepository.findById(newUserInfo.getId()).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
			user.setName(newUserInfo.getName());
			user.setUsername(newUserInfo.getUsername());

			if (newUserInfo.getActive() != null) {
				user.setActive(newUserInfo.getActive());
			}

			if (newUserInfo.getPassword() != null) {
				user.setPassword(encoder.encode(newUserInfo.getPassword()));
			}
			log.info("{} kullanıcısı güncellendi.", user.getUsername());
			userRepository.save(user);
		} else {
			log.info("{} kullanıcı adında başka biri zaten bulunmakta.", newUserInfo.getUsername());
			throw new FnthlException(ErrorMessage.USER_EXIST);
		}

	}

	@Transactional(rollbackFor = Exception.class)
	public String setPassiveUsers(List<Long> idList) {
		boolean isUsersExists = userRepository.existsByIdList(idList, (long) idList.size());
		if (isUsersExists) {
			List<User> users = userRepository.getUserListByIdList(idList);
			for (User user : users) {
				user.setActive(false);
			}
			userRepository.saveAll(users);
			log.info("{} id'li kullanıcılar pasife alındı.", idList);
			return "Kullanıcı pasifleştirme işlemi başarılı.";
		} else {
			log.info("{} id'lerin bazıları sistemde bulunamadı.", idList);
			throw new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND);
		}
	}

	public void resetPassword(String oldPassword, String newPassword, String username) {
		if (!oldPassword.equals(newPassword)) {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				if (encoder.matches(oldPassword, user.getPassword())) {
					user.setPassword(encoder.encode(newPassword));
					log.info("{} kullanıcının şifresi değiştirildi.", username);
					userRepository.save(user);
				} else {
					log.info("{} kullanıcının eski şifresi uyuşmadı.", username);
					throw new FnthlException(ErrorMessage.USER_WRONG_OLD_PASSWORD);
				}
			} else {
				log.info("{} kullanıcısı bulunamadı.", username);
				throw new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND);
			}
		} else {
			log.info("Eski şifre ve yeni şifre uyumsuz.");
			throw new FnthlException(ErrorMessage.USER_SAME_PASSWORD_WITH_OLD_PASSWORD);
		}
	}

	public PageableResponse<User> searchUsers(Integer pageNo, Integer pageSize, String searchParam, String orderBy, String orderDirection) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(orderDirection), orderBy));
		Page<User> page = userRepository.findUsers(searchParam, paging);
		return new PageableResponse<>(page);
	}

}
