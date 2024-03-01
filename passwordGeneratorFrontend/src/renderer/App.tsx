/* eslint-disable prettier/prettier */
import { NextUIProvider } from "@nextui-org/react";
import React, { FunctionComponent } from "react";
import { Provider } from "react-redux";
import Landing from "./Landing";
import { store } from "./Store/store";
import "./dist/output.css";

const App: FunctionComponent = (props) => {
	return (
		<Provider store={store}>
			<NextUIProvider
				className="w-full h-full"
				style={{
					backgroundColor: "transparent",
				}}
			>
				<main
					className="dark text-foreground bg-background w-full h-full"
					style={{
						backgroundColor: "transparent",
					}}
				>
					<Landing />
				</main>
			</NextUIProvider>
		</Provider>
	);
};

export default App;
