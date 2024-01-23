/* eslint-disable prettier/prettier */
import { NextUIProvider } from "@nextui-org/react";
import React, { FunctionComponent } from "react";
import { Provider } from "react-redux";
import "./App.css";
import Loading from "./Loading";
import { store } from "./Store/store";

const App: FunctionComponent = (props) => {
	return (
		<Provider store={store}>
			<NextUIProvider
				style={{
					backgroundColor: "transparent",
				}}
			>
				<main
					className="dark text-foreground bg-background"
					style={{
						backgroundColor: "transparent",
					}}
				>
					<Loading />
				</main>
			</NextUIProvider>
		</Provider>
	);
};

export default App;
