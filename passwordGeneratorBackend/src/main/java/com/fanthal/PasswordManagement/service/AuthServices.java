package com.fanthal.PasswordManagement.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fanthal.PasswordManagement.dto.JwtResponse;
import com.fanthal.PasswordManagement.dto.LoginDto;
import com.fanthal.PasswordManagement.dto.TokenRefreshRequest;
import com.fanthal.PasswordManagement.dto.TokenRefreshResponse;
import com.fanthal.PasswordManagement.exceptions.FnthlException;
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
public class AuthServices {
	final RefreshTokenServices refreshTokenService;
	final UserRepository userRepository;

	final AuthenticationManager authenticationManager;

	final PasswordEncoder encoder;

	final JwtUtils jwtUtils;

	public JwtResponse loginUser(LoginDto user) {
		User existUser = userRepository.findByUsername(user.getUsername());

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (existUser.getActive()) {

			String jwt = jwtUtils.generateJwtToken(user.getUsername());

			RefreshToken refreshToken = refreshTokenService.createRefreshToken(existUser.getId());

			return new JwtResponse(jwt, refreshToken.getToken(), existUser.getId(), existUser.getUsername());

		} else {
			throw new FnthlException(ErrorMessage.USER_NOT_ACTIVE);
		}

	}

	public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();
		RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);
		if (refreshToken != null) {
			if (refreshTokenService.verifyExpiration(refreshToken)) {
				User user = userRepository.getById(refreshToken.getUser().getId());
				String token = jwtUtils.generateTokenFromUsername(user.getUsername());
				log.info("Refresh tokeni kullanılarak yeni access token üretildi.");
				return new TokenRefreshResponse(token, requestRefreshToken);
			} else {
				log.info("Refresh tokenin süresi dolmuş.");
				throw new FnthlException(ErrorMessage.REFRESH_TOKEN_EXPIRED);
			}
		}
		log.info("Refresh tokeni bulunamadı.");
		throw new FnthlException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND);
	}

}
