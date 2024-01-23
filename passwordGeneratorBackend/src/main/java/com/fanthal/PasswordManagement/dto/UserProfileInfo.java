package com.fanthal.PasswordManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileInfo {

	private Long id;
	private String username;
	private String name;
	private Boolean active;
}
