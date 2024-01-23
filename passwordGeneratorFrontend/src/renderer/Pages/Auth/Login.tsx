import { Button, Checkbox, Image, Input } from "@nextui-org/react";
import { useFormik } from "formik";
import React, { FunctionComponent, useState } from "react";
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";
import * as Yup from "yup";
import { ScreenType } from "../../Constants/Types";
import { LoginDto } from "../../DTO/AuthDTO";
import { userLogin } from "../../Services/authService";
import AuthUserActions from "../../Store/Actions/AuthActions";
import ScreenActions from "../../Store/Actions/ScreenActions";
import { useFanthalDispatch } from "../../Store/hooks";
import { LocalStorageKeys } from "../../Utils/types";

const Login: FunctionComponent = () => {
	const initialValues: LoginDto = { username: "", password: "" };
	const dispatch = useFanthalDispatch();
	const [rememberMe, setRememberMe] = useState<boolean>(false);

	const [isVisiblePass, setIsVisiblePass] = useState(false);
	const {
		getFieldProps,
		handleSubmit,
		values,
		errors,
		touched,
		setFieldValue,
		setTouched,
	} = useFormik({
		initialValues,
		onSubmit: (values: LoginDto) => {
			userLogin(
				values,
				(res) => {
					dispatch(AuthUserActions.updateAuthUser(res));
					dispatch(ScreenActions.updateActiveScreen(ScreenType.Layout));
					if (rememberMe) {
						localStorage.setItem(
							LocalStorageKeys.AUTH,
							JSON.stringify(res)
						);
					}
				},
				(err) => {
					console.error(err);
				}
			);
		},
		onReset: (e) => {},
		validationSchema: Yup.object().shape({
			username: Yup.string().required("Enter a username").nullable(),
			password: Yup.string().required("Enter a password").nullable(),
		}),
		validateOnChange: true,
	});

	return (
		<div
			style={{
				width: "100%",
				height: "100%",
				backgroundColor: "transparent",
			}}
		>
			<div
				style={{
					backgroundColor: "transparent",
					display: "flex",
					flexDirection: "column",
					alignItems: "center",
					justifyContent: "center",
				}}
			>
				<div style={{ margin: 7, width: 250 }}>
					<Image
						width={300}
						alt="Fanthal"
						src={require("../../Assets/Logo512x512.png")}
					/>
				</div>
				<div style={{ margin: 7, width: 250 }}>
					<Input
						{...getFieldProps("username")}
						size="sm"
						label="Username"
						placeholder="Username"
						isInvalid={
							touched.username === true && errors.username !== undefined
						}
						errorMessage={errors.username}
						onChange={(val) => {
							setFieldValue("username", val.target.value);
							setTouched({ username: true });
						}}
						onBlur={(val) => {
							setTouched({ username: true });
						}}
					/>
				</div>
				<div style={{ margin: 7, width: 250 }}>
					<Input
						{...getFieldProps("password")}
						size="sm"
						type={isVisiblePass ? "text" : "password"}
						label="Password"
						labelPlacement="inside"
						placeholder="Password"
						isInvalid={
							touched.password === true && errors.password !== undefined
						}
						errorMessage={errors.password}
						onChange={(val) => {
							setFieldValue("password", val.target.value);
							setTouched({ password: true });
						}}
						onBlur={(val) => {
							setTouched({ password: true });
						}}
						endContent={
							<button
								className="focus:outline-none"
								type="button"
								onClick={() => {
									setIsVisiblePass(!isVisiblePass);
								}}
							>
								{isVisiblePass ? (
									<AiFillEyeInvisible className="text-2xl text-default-400 pointer-events-none" />
								) : (
									<AiFillEye className="text-2xl text-default-400 pointer-events-none" />
								)}
							</button>
						}
					/>
				</div>
				<div style={{ margin: 7, width: 250, paddingLeft: 10 }}>
					<Checkbox
						defaultSelected
						size="sm"
						isSelected={rememberMe}
						onValueChange={(val) => {
							setRememberMe(val);
						}}
					>
						Remember me!
					</Checkbox>
				</div>
				<div
					style={{
						marginTop: 7,
						width: 250,
						display: "flex",
						flexDirection: "row",
						alignItems: "center",
						justifyContent: "space-around",
					}}
				>
					<Button
						onClick={() => {
							handleSubmit();
						}}
						style={{ width: "100%" }}
						type="submit"
						color="primary"
					>
						Login
					</Button>
				</div>
				<div
					style={{
						marginTop: 7,
						width: 250,
						display: "flex",
						flexDirection: "row",
						alignItems: "center",
						justifyContent: "space-around",
					}}
				>
					<Button
						style={{ width: "100%" }}
						color="primary"
						variant="ghost"
						onClick={() => {
							dispatch(
								ScreenActions.updateActiveScreen(ScreenType.Register)
							);
						}}
					>
						Register
					</Button>
				</div>
			</div>
		</div>
	);
};
export default Login;
