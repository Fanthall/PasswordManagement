import React, { FunctionComponent, useEffect } from "react";

const Layout: FunctionComponent = () => {
	useEffect(() => {
		window.electron.ipcRenderer.sendMessage("change-size", [1024, 720]);
	}, []);
	return (
		<>
			<div>Layout</div>
		</>
	);
};
export default Layout;
