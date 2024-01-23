package com.fanthal.PasswordManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fanthal.PasswordManagement.dto.JwtResponse;
import com.fanthal.PasswordManagement.dto.RegisterUserDTO;
import com.fanthal.PasswordManagement.dto.UserProfileInfo;
import com.fanthal.PasswordManagement.mapper.UserMapper;
import com.fanthal.PasswordManagement.model.User;
import com.fanthal.PasswordManagement.service.UserServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/1/user")
@RequiredArgsConstructor
public class UserController {
	final UserServices userServices;

	@PostMapping
	public void createUser(@RequestBody RegisterUserDTO user) {
		userServices.createUser(UserMapper.fromRegisterUserDTO(user));
	}

	@PutMapping
	public void updateUser(@RequestBody User user) {
		userServices.updateUser(user);
	}

	@GetMapping("/{id}")
	public UserProfileInfo getUser(@PathVariable Long userId) {
		return UserMapper.toUserProfileInfo(userServices.getUser(userId));
	}

	@GetMapping("/auth-user/{id}")
	public ResponseEntity<JwtResponse> getAuthUser(@PathVariable Long id) {
		return ResponseEntity.ok(userServices.getAuthUser(id));
	}

}
