import axios, { AxiosRequestConfig, AxiosResponse } from "axios";
import { RefreshTokenInterface } from "../DTO/AuthDTO";
import { AuthUser } from "../Store/Reducers/AuthReducer";
import { LocalStorageKeys } from "../Utils/types";
const BASE_URL = "http://localhost:8080/";
const BASE_HEADER = {
	"Access-Control-Allow-Origin": "*",
	"Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
};

export function makeRequest<T>(
	config: AxiosRequestConfig,
	accessToken: string
): Promise<AxiosResponse<any>> {
	return axios
		.request<T>({
			baseURL: `${BASE_URL}1/`,
			headers: {
				...BASE_HEADER,
				Authorization: `Bearer ${accessToken}`,
			},
			...config,
		})
		.then((res) => {
			return Promise.resolve(res);
		})
		.catch((err) => {
			if (err.response.status === 401) {
				const localData = localStorage.getItem(LocalStorageKeys.AUTH);
				if (localData) {
					const localAuth: AuthUser = JSON.parse(localData);
					makeAuthRequest<RefreshTokenInterface>({
						method: "POST",
						url: "refresh-token",
						data: { refreshToken: localAuth.refreshToken },
					})
						.then((res) => {
							console.log("makeAuthRequest");
							localStorage.setItem(
								LocalStorageKeys.AUTH,
								JSON.stringify({ ...localAuth, ...res.data })
							);
							console.log("then");
							return makeRequest<T>(config, res.data.accessToken);
						})
						.catch((err) => {
							console.log("err");
							if (
								["SDD_101", "SDD_102", "SDD_001"].some(
									(code) => code === err.code
								)
							) {
								localStorage.removeItem(LocalStorageKeys.AUTH);
								return;
							}
							return Promise.reject(err);
						});
				}
			}
			return Promise.reject(err);
		});
}

export function makeAuthRequest<T>(config: AxiosRequestConfig) {
	return axios.request<T>({
		headers: {
			...BASE_HEADER,
		},
		baseURL: `${BASE_URL}auth/`,
		...config,
	});
}
