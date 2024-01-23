import { configureStore } from "@reduxjs/toolkit";
import AuthReducer from "./Reducers/AuthReducer";
import ScreenReducer from "./Reducers/ScreenReducer";
const rootReducer = {
	auth: AuthReducer,
	screen: ScreenReducer,
};

export const store = configureStore({
	reducer: rootReducer,
	middleware(getDefaultMiddleware) {
		return getDefaultMiddleware({ serializableCheck: false });
	},
});

export type RootState = ReturnType<typeof store.getState>;

export type FanthalDispatch = typeof store.dispatch;
