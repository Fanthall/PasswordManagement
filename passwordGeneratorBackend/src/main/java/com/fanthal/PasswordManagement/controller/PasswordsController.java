package com.fanthal.PasswordManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fanthal.PasswordManagement.dto.PageableResponse;
import com.fanthal.PasswordManagement.dto.PasswordsDTO;
import com.fanthal.PasswordManagement.mapper.PasswordMapper;
import com.fanthal.PasswordManagement.model.Password;
import com.fanthal.PasswordManagement.service.PasswordsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/1/paswords")
@RequiredArgsConstructor
public class PasswordsController {

	final PasswordsService passwordsService;

	@PostMapping
	public void createePassword(Authentication authentication, @RequestBody PasswordsDTO newPassword) {
		passwordsService.createPassword(newPassword, authentication);
	}

	@PutMapping
	public void updatePassword(Authentication authentication, @RequestBody PasswordsDTO newPassword) {
		passwordsService.updatePassword(newPassword, authentication);
	}

	@DeleteMapping
	public void deletePassword(Authentication authentication, @RequestBody PasswordsDTO newPassword) {
		passwordsService.deletePassword(newPassword, authentication);
	}

	@GetMapping
	public ResponseEntity<com.fanthal.PasswordManagement.dto.PageableResponse<PasswordsDTO>> searchPassword(
			@RequestParam("search-param") String searchParam,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(name = "orderByField", defaultValue = "id") String orderBy,
			@RequestParam(defaultValue = "asc") String orderDirection, Authentication authentication) {
		PageableResponse<Password> paswords = passwordsService.searchPasswords(searchParam, page, size, orderBy, orderDirection, authentication);
		return new ResponseEntity<>(
				new PageableResponse<>(PasswordMapper.toPasswordsDTOList(paswords.getItemList()), paswords.getCurrentPage(), paswords.getTotalPages(),
						paswords.getTotalItems()),
				HttpStatus.OK);
	}
}
