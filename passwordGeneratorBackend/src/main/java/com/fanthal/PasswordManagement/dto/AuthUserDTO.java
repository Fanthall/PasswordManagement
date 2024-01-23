package com.fanthal.PasswordManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUserDTO {
	private Long id;
	private String username;
	private String name;
}
