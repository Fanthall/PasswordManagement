package com.fanthal.PasswordManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fanthal.PasswordManagement.dto.JwtResponse;
import com.fanthal.PasswordManagement.dto.LoginDto;
import com.fanthal.PasswordManagement.dto.RegisterUserDTO;
import com.fanthal.PasswordManagement.dto.TokenRefreshRequest;
import com.fanthal.PasswordManagement.dto.TokenRefreshResponse;
import com.fanthal.PasswordManagement.mapper.UserMapper;
import com.fanthal.PasswordManagement.service.AuthServices;
import com.fanthal.PasswordManagement.service.UserServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthUserController {
	final AuthServices authServices;

	final UserServices userServices;

	@PostMapping("/register")
	public void registeUser(@RequestBody RegisterUserDTO user) {
		userServices.createUser(UserMapper.fromRegisterUserDTO(user));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> LoginUser(@RequestBody LoginDto user) {

		return ResponseEntity.ok(authServices.loginUser(user));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<TokenRefreshResponse> refreshToken(@Validated @RequestBody TokenRefreshRequest request) {
		return ResponseEntity.ok(authServices.refreshToken(request));
	}

}
