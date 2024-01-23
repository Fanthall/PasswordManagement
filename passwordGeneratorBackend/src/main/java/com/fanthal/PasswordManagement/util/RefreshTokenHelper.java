package com.fanthal.PasswordManagement.util;

import java.util.UUID;

public class RefreshTokenHelper {

	public static Long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
}
