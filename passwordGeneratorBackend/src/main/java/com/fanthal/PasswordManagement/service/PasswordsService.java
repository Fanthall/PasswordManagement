package com.fanthal.PasswordManagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fanthal.PasswordManagement.dto.PageableResponse;
import com.fanthal.PasswordManagement.dto.PasswordsDTO;
import com.fanthal.PasswordManagement.exceptions.FnthlException;
import com.fanthal.PasswordManagement.model.Password;
import com.fanthal.PasswordManagement.model.User;
import com.fanthal.PasswordManagement.repository.PasswordsRepository;
import com.fanthal.PasswordManagement.repository.UserRepository;
import com.fanthal.PasswordManagement.type.ErrorMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PasswordsService {
	private final PasswordsRepository passwordsRepository;
	private final UserRepository userRepository;

	public void createPassword(PasswordsDTO newPassword, Authentication authentication) {
		User user = userRepository.findByUsername(authentication.getName());
		boolean exist = passwordsRepository.existByUsernameAndUserId(newPassword.getName(), user.getId());
		if (!exist) {
			Password password = new Password();
			password.setName(newPassword.getName());
			password.setPassword(newPassword.getPassword());
			password.setUsername(newPassword.getUsername());
			password.setUser(user);
			passwordsRepository.save(password);
		} else {
			throw new FnthlException(ErrorMessage.PASSWORD_EXIST);
		}
	}

	public void updatePassword(PasswordsDTO newPassword, Authentication authentication) {
		User user = userRepository.findByUsername(authentication.getName());
		Password existPassword = passwordsRepository.findByUsername(newPassword.getName());
		if (existPassword != null) {
			if (existPassword.getUser().getId().equals(user.getId())) {
				existPassword.setName(newPassword.getName());
				existPassword.setPassword(newPassword.getPassword());
				existPassword.setUsername(newPassword.getUsername());
				existPassword.setUser(user);
				passwordsRepository.save(existPassword);
			} else {
				throw new FnthlException(ErrorMessage.NOT_HAVE_PERMISSION_FOR_DELETE);
			}
		} else {
			throw new FnthlException(ErrorMessage.PASSWORD_NOT_EXIST);
		}
	}

	public void deletePassword(PasswordsDTO newPassword, Authentication authentication) {
		User user = userRepository.findByUsername(authentication.getName());
		Password existPassword = passwordsRepository.findByUsername(newPassword.getName());
		if (existPassword != null) {
			if (existPassword.getUser().getId().equals(user.getId())) {
				passwordsRepository.delete(existPassword);
			} else {
				throw new FnthlException(ErrorMessage.NOT_HAVE_PERMISSION_FOR_DELETE);
			}
		} else {
			throw new FnthlException(ErrorMessage.PASSWORD_NOT_EXIST);
		}
	}

	public PageableResponse<Password> searchPasswords(String searchParam, Integer page, Integer size, String orderBy, String orderDirection,
			Authentication authentication) {
		User user = userRepository.findByUsername(authentication.getName());
		Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderDirection), orderBy));
		Page<Password> result = passwordsRepository.searchPasswordsWithuserId(searchParam, paging);
		return new PageableResponse<>(result);
	}

}
