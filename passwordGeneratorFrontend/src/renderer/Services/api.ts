import Axios from "axios";
import { AuthUser } from "../Store/Reducers/AuthReducer";
import { LocalStorageKeys } from "../Utils/types";

const instance = () => {
	const instance = Axios.create({
		baseURL: `${process.env.REACT_APP_API_BASE_URI}1/`,
		headers: {
			"Access-Control-Allow-Origin": "*",
			"Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
		},
	});
	instance.interceptors.response.use(
		(response: any) => {
			return response;
		},
		(error) => {
			if (error.response.status === 401) {
				const localData = localStorage.getItem(LocalStorageKeys.AuthInfo);
				if (localData) {
					const localAuth: AuthUser = JSON.parse(localData);
					instance
						.post("auth/refresh-token", {
							refreshToken: localAuth.refreshToken,
						})
						.then((res) => {
							localStorage.setItem(
								LocalStorageKeys.AuthInfo,
								JSON.stringify({ ...localAuth, ...res.data })
							);
							//return instance.request(instance.request., res.data.accessToken);
						})
						.catch((err) => {
							return Promise.reject(err);
						});
				}
			}
			return Promise.reject(error);
		}
	);
	return instance;
};

export default instance;
