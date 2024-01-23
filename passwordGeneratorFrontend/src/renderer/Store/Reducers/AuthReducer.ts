import { LocalStorageKeys } from "../../Utils/types";
import { AuthAction } from "../Actions/AuthActions";
import { AuthActionTypes } from "../Types/AuthActionTypes";

export type AuthUser = {
	id: number;
	name: string;
	username: string;
	key1: string;
	key2: string;
	token: string;
	refreshToken: string;
};

const initialState: AuthUser = {
	id: -1,
	name: "",
	username: "",
	key1: "",
	key2: "",
	token: "",
	refreshToken: "",
};

const ScreenReducer = (state = initialState, action: AuthAction): AuthUser => {
	switch (action.type) {
		case AuthActionTypes.UPDATE_AUTH_USER:
			return { ...action.newAuthUser };
		case AuthActionTypes.REMOVE_AUTH_USER:
			localStorage.removeItem(LocalStorageKeys.AUTH);
			return { ...initialState };
		default:
			return state;
	}
};

export default ScreenReducer;
