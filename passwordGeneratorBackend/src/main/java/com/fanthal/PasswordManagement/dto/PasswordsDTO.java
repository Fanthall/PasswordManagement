package com.fanthal.PasswordManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordsDTO {
	private Long id;
	private String username;
	private String name;
	private String password;
}
