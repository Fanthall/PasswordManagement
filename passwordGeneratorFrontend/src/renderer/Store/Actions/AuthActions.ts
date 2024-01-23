// Redux-store type bağımlılığında action type'ını 'AnyAction' olarak tanımlanmış. bkz. Redux AnyAction interface
// Bu interface tip olarak sadece type alanı içeren bir interface olduğu için, action interface'lerinin en az 1 tanesinde
// sadece type alanı içeren bir interface olması type bağımlılığının sağlanabilmesi için gerekiyor. Aşağıdaki AnyAction

import { AuthUser } from "../Reducers/AuthReducer";
import { AuthActionTypes } from "../Types/AuthActionTypes";
import { FanthalDispatch } from "../store";

interface AnyAction {
	type: "ANY_ACTION";
}

interface UpdateAuthAction {
	type: AuthActionTypes.UPDATE_AUTH_USER;
	newAuthUser: AuthUser;
}
interface RemoveAuthAction {
	type: AuthActionTypes.REMOVE_AUTH_USER;
}
export type AuthAction = AnyAction | UpdateAuthAction | RemoveAuthAction;

const updateAuthUserAction = (newAuthUser: AuthUser): AuthAction => {
	return {
		type: AuthActionTypes.UPDATE_AUTH_USER,
		newAuthUser: newAuthUser,
	};
};

export const updateAuthUser = (newAuthUser: AuthUser) => {
	return (dispatch: FanthalDispatch) => {
		dispatch(updateAuthUserAction(newAuthUser));
	};
};

const removeAuthUserAction = (newAuthUser: AuthUser): AuthAction => {
	return {
		type: AuthActionTypes.UPDATE_AUTH_USER,
		newAuthUser: newAuthUser,
	};
};

export const removeAuthUser = (newAuthUser: AuthUser) => {
	return (dispatch: FanthalDispatch) => {
		dispatch(removeAuthUserAction(newAuthUser));
	};
};

const AuthUserActions = {
	updateAuthUser,
	removeAuthUser,
};

export default AuthUserActions;
