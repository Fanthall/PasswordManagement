// Redux-store type bağımlılığında action type'ını 'AnyAction' olarak tanımlanmış. bkz. Redux AnyAction interface
// Bu interface tip olarak sadece type alanı içeren bir interface olduğu için, action interface'lerinin en az 1 tanesinde
// sadece type alanı içeren bir interface olması type bağımlılığının sağlanabilmesi için gerekiyor. Aşağıdaki AnyAction

import { ScreenType } from "../../Constants/Types";
import { ScreenActionTypes } from "../Types/ScreenActionTypes";
import { FanthalDispatch } from "../store";

// interface'i bunun için tanımlandı ve ScreenAction type'ına eklendi.
interface AnyAction {
	type: "ANY_ACTION";
}

interface UpdateActiveScreenAction {
	type: ScreenActionTypes.UPDATE_ACTIVE_SCREEN;
	newScreen: ScreenType;
}

export type ScreenAction = AnyAction | UpdateActiveScreenAction;

const updateActiveScreenAction = (newScreen: ScreenType): ScreenAction => {
	return {
		type: ScreenActionTypes.UPDATE_ACTIVE_SCREEN,
		newScreen: newScreen,
	};
};

export const updateActiveScreen = (newScreen: ScreenType) => {
	return (dispatch: FanthalDispatch) => {
		dispatch(updateActiveScreenAction(newScreen));
	};
};

const ScreenActions = {
	updateActiveScreen,
};

export default ScreenActions;
