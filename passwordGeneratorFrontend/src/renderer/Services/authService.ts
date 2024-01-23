import { LoginDto, RegisterUserInfo } from "../DTO/AuthDTO";
import { AuthUser } from "../Store/Reducers/AuthReducer";
import { makeAuthRequest } from "./makeRequest";
export const userLogin = (
	userData: LoginDto,

	successCallback: (res: AuthUser) => void,
	errorCallback: (err: any) => void
) => {
	makeAuthRequest<AuthUser>({
		url: "login",
		method: "POST",
		data: userData,
	})
		.then((res) => {
			successCallback(res.data);
		})
		.catch((err) => errorCallback(err));
};

export const registerUser = (
	user: RegisterUserInfo,
	successCallback: (res: RegisterUserInfo) => void,
	errorCallback: (err: any) => void
) => {
	makeAuthRequest<RegisterUserInfo>({
		url: "register",
		method: "POST",
		data: { ...user },
	})
		.then((res) => {
			successCallback(res.data);
		})
		.catch((err) => errorCallback(err));
};
