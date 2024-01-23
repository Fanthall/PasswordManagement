import { Spinner } from "@nextui-org/react";
import React, { FunctionComponent, useEffect, useState } from "react";
import Main from "./Pages/Auth/Main";
import Layout from "./Pages/Layout/Layout";
import { getAuthUserService } from "./Services/userService";
import AuthUserActions from "./Store/Actions/AuthActions";
import { AuthUser } from "./Store/Reducers/AuthReducer";
import { useFanthalDispatch, useFanthalSelector } from "./Store/hooks";
import { LocalStorageKeys } from "./Utils/types";

const Loading: FunctionComponent = () => {
	const { auth } = useFanthalSelector((store) => store);
	const dispatch = useFanthalDispatch();
	const [loading, setLoading] = useState<boolean>(false);
	const loginWithLocalAuth = async () => {
		const jsonString = await localStorage.getItem(LocalStorageKeys.AUTH);
		if (jsonString) {
			const localAuth = JSON.parse(jsonString);
			getAuthUserService(localAuth.id, localAuth.accessToken)
				.then((res: AuthUser) => {
					dispatch(AuthUserActions.updateAuthUser(res));
					localStorage.setItem(LocalStorageKeys.AUTH, JSON.stringify(res));
				})
				.catch((err) => {
					console.error(err);
				})
				.finally(() => {
					setLoading(false);
				});
		} else {
			setLoading(false);
		}
	};
	useEffect(() => {
		if (auth.id === -1) {
			setLoading(true);
			loginWithLocalAuth();
		}
	}, []);
	if (loading) {
		return <Spinner></Spinner>;
	}
	if (auth.id === -1)
		//giriş yapılmamış
		return (
			<>
				<Main />
			</>
		);
	return (
		<>
			<Layout />
		</>
	);
};
export default Loading;
