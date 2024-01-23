import { LoginDto, RegisterUserInfo } from "../DTO/AuthDTO";
import { AuthUser } from "../Store/Reducers/AuthReducer";
import axios from "./api";

export const registerUser = (registerUser: RegisterUserInfo) => {
	return axios().post("auth/register", registerUser);
};

export const loginUser = (registerUser: LoginDto) => {
	return axios().post<AuthUser>("auth/login", registerUser);
};
export const loginWithAccessToken = (userId: number, refreshToken: string) => {
	return axios().get<AuthUser>("auth/auth-user", {
		params: { "user-id": userId, "refresh-token": refreshToken },
	});
};
