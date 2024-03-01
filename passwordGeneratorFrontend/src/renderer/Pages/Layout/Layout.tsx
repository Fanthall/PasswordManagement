import { Button, ButtonGroup } from "@nextui-org/react";
import React, { FunctionComponent, useEffect } from "react";
import { ScreenType } from "../../Constants/Types";
import AuthUserActions from "../../Store/Actions/AuthActions";
import ScreenActions from "../../Store/Actions/ScreenActions";
import { useFanthalDispatch, useFanthalSelector } from "../../Store/hooks";
import Account from "../Account/Account";
import Config from "../Config/Config";
import Generate from "../Generate/Generate";
import Home from "../Home";
import Passwords from "../Passwords/Passwords";

const Layout: FunctionComponent = () => {
	useEffect(() => {
		window.electron.ipcRenderer.sendMessage("change-size", [1024, 720]);
	}, []);
	const screen = useFanthalSelector((store) => store.screen);
	const dispatch = useFanthalDispatch();
	console.log(screen);
	return (
		<div className="w-full h-full  flex flex-col justify-center items-center">
			<div className=" flex flex-row justify-between items-center gap-6 m-7">
				<ButtonGroup>
					<Button
						onClick={() => {
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Layout)
							);
						}}
					>
						Home
					</Button>
					<Button
						onClick={() => {
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Generate)
							);
						}}
					>
						Generate
					</Button>
					<Button
						onClick={() => {
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Passwords)
							);
						}}
					>
						Passwords
					</Button>
					<Button
						onClick={() => {
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Config)
							);
						}}
					>
						Config
					</Button>
					<Button
						onClick={() => {
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Account)
							);
						}}
					>
						Account
					</Button>
					<Button
						onClick={() => {
							dispatch(AuthUserActions.removeAuthUser());
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Login)
							);
						}}
					>
						Exit
					</Button>
				</ButtonGroup>
			</div>
			<div className="w-full h-full flex flex-row justify-center items-start p-5">
				{screen.activeScreen === ScreenType.Layout && <Home />}
				{screen.activeScreen === ScreenType.Generate && <Generate />}
				{screen.activeScreen === ScreenType.Passwords && <Passwords />}
				{screen.activeScreen === ScreenType.Config && <Config />}
				{screen.activeScreen === ScreenType.Account && <Account />}
			</div>
		</div>
	);
};
export default Layout;
