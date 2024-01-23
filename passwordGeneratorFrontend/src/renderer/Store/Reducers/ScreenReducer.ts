import { ScreenType } from "../../Constants/Types";
import { ScreenAction } from "../Actions/ScreenActions";
import { ScreenActionTypes } from "../Types/ScreenActionTypes";

type ScreenState = {
	activeScreen: ScreenType;
};

const initialState: ScreenState = {
	activeScreen: ScreenType.Login,
};

const ScreenReducer = (
	state = initialState,
	action: ScreenAction
): ScreenState => {
	switch (action.type) {
		case ScreenActionTypes.UPDATE_ACTIVE_SCREEN:
			return { ...state, activeScreen: action.newScreen };
		default:
			return state;
	}
};

export default ScreenReducer;
