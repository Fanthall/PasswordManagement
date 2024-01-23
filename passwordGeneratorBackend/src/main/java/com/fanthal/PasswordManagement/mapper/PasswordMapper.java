package com.fanthal.PasswordManagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fanthal.PasswordManagement.dto.PasswordsDTO;
import com.fanthal.PasswordManagement.model.Password;

public class PasswordMapper {

	private PasswordMapper() {
	}

	public static PasswordsDTO toPasswordsDTO(Password password) {
		PasswordsDTO result = new PasswordsDTO();
		result.setId(password.getId());
		result.setUsername(password.getUsername());
		result.setName(password.getName());
		return result;
	}

	public static Password fromPasswordsDTO(PasswordsDTO passwordsDTO) {
		Password result = new Password();
		result.setId(passwordsDTO.getId());
		result.setUsername(passwordsDTO.getUsername());
		result.setName(passwordsDTO.getName());
		return result;
	}

	public static List<PasswordsDTO> toPasswordsDTOList(List<Password> paswords) {
		List<PasswordsDTO> passwordList = new ArrayList<>();
		for (Password password : paswords) {
			passwordList.add(toPasswordsDTO(password));
		}
		return passwordList;
	}

}
