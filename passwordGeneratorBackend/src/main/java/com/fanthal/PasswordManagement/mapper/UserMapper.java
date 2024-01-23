package com.fanthal.PasswordManagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fanthal.PasswordManagement.dto.AuthUserDTO;
import com.fanthal.PasswordManagement.dto.RegisterUserDTO;
import com.fanthal.PasswordManagement.dto.UserDTO;
import com.fanthal.PasswordManagement.dto.UserProfileInfo;
import com.fanthal.PasswordManagement.model.User;

public class UserMapper {

	private UserMapper() {
	}

	public static AuthUserDTO toAuthUserDTO(User user) {
		AuthUserDTO result = new AuthUserDTO();
		result.setId(user.getId());
		result.setUsername(user.getUsername());
		result.setName(user.getName());
		return result;
	}

	public static User fromAuthUserDTO(AuthUserDTO authUser) {
		User result = new User();
		result.setId(authUser.getId());
		result.setUsername(authUser.getUsername());
		result.setName(authUser.getName());
		return result;
	}

	public static User fromRegisterUserDTO(RegisterUserDTO newUser) {
		User result = new User();
		result.setUsername(newUser.getUsername());
		result.setName(newUser.getName());
		result.setPassword(newUser.getPassword());
		result.setActive(false);
		return result;
	}

	public static UserProfileInfo toUserProfileInfo(User user) {
		UserProfileInfo userInfo = new UserProfileInfo();
		userInfo.setId(user.getId());
		userInfo.setName(user.getName());
		userInfo.setUsername(user.getUsername());
		userInfo.setActive(user.getActive());
		return userInfo;
	}

	public static UserDTO toUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setActive(user.getActive());
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}

	public static List<UserDTO> toUserDtoList(List<User> users) {
		List<UserDTO> userList = new ArrayList<>();
		for (User user : users) {
			userList.add(toUserDTO(user));
		}
		return userList;
	}

}
