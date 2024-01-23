export interface LoginDto {
	username: string;
	password: string;
}

export interface RegisterUserInfo {
	name: string;
	username: string;
	password: string;
}

export interface RefreshTokenInterface {
	accessToken: string;
	refreshToken: string;
	tokenType: string;
}
