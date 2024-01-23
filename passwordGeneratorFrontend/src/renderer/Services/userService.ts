import { AuthUser } from "../Store/Reducers/AuthReducer";
import { makeRequest } from "./makeRequest";

export const getAuthUserService = (userId: number, accessToken: string) => {
	return makeRequest<AuthUser>(
		{ method: "get", url: `user/auth-user/${userId}` },
		accessToken
	)
		.then((res) => res)
		.catch((err) => err);
};
