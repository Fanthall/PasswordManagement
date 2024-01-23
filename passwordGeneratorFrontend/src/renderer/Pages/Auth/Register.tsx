import { Button, Image, Input } from "@nextui-org/react";
import { useFormik } from "formik";
import React, { FunctionComponent, useState } from "react";
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";
import * as Yup from "yup";
import { ScreenType } from "../../Constants/Types";
import { RegisterUserInfo } from "../../DTO/AuthDTO";
import { registerUser } from "../../Services/authService";
import ScreenActions from "../../Store/Actions/ScreenActions";
import { useFanthalDispatch } from "../../Store/hooks";

const Register: FunctionComponent = () => {
	const [isVisiblePass, setIsVisiblePass] = useState(false);
	const initialValues: RegisterUserInfo = {
		username: "",
		password: "",
		name: "",
	};
	const dispatch = useFanthalDispatch();
	const {
		getFieldProps,
		handleSubmit,
		values,
		errors,
		touched,
		setFieldValue,
		setTouched,
	} = useFormik({
		initialValues: initialValues,
		onSubmit: (newValues: RegisterUserInfo) => {
			registerUser(
				newValues,
				(res) => {
					dispatch(ScreenActions.updateActiveScreen(ScreenType.Login));
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
						src={require("./../../Assets/Logo512x512.png")}
					/>
				</div>
				<div style={{ margin: 7, width: 250 }}>
					<Input
						{...getFieldProps("name")}
						size={"sm"}
						label={"Name"}
						placeholder={"Name"}
						isInvalid={touched.name === true && errors.name !== undefined}
						errorMessage={errors.name}
						onChange={(val) => {
							setFieldValue("name", val.target.value);
							setTouched({ name: true });
						}}
						onBlur={(val) => {
							setTouched({ name: true });
						}}
					></Input>
				</div>
				<div style={{ margin: 7, width: 250 }}>
					<Input
						{...getFieldProps("username")}
						size={"sm"}
						label={"Username"}
						placeholder={"Username"}
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
					></Input>
				</div>
				<div style={{ margin: 7, width: 250 }}>
					<Input
						{...getFieldProps("password")}
						size={"sm"}
						type={isVisiblePass ? "text" : "password"}
						label={"Password"}
						labelPlacement={"inside"}
						placeholder={"Password"}
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
					></Input>
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
						color="primary"
						type="submit"
					>
						Register
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
								ScreenActions.updateActiveScreen(ScreenType.Login)
							);
						}}
					>
						Login Page
					</Button>
				</div>
			</div>
		</div>
	);
};
export default Register;
