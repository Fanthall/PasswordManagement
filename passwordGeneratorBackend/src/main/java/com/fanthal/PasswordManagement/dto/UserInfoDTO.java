package com.fanthal.PasswordManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
	private Long id;

	private String username;

	private String name;

	private boolean active;

}
