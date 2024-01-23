import React, { FunctionComponent, useEffect } from "react";
import { ScreenType } from "../../Constants/Types";
import { useFanthalSelector } from "../../Store/hooks";
import Login from "./Login";
import Register from "./Register";

const Main: FunctionComponent = () => {
	const { screen } = useFanthalSelector((store) => store);
	useEffect(() => {
		window.electron.ipcRenderer.sendMessage("change-size", [500, 700]);
	}, []);
	if (screen.activeScreen === ScreenType.Login) {
		return <Login />;
	} else if (screen.activeScreen === ScreenType.Register) {
		return <Register />;
	}

	return <></>;
};
export default Main;
