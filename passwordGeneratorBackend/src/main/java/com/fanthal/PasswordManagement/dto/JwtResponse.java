package com.fanthal.PasswordManagement.dto;

import lombok.Data;

@Data
public class JwtResponse {
	private String accessToken;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String username;

	public JwtResponse(String accessToken, String refreshToken, Long id, String username) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
	}

}
