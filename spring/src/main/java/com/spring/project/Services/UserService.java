package com.spring.project.Services;

import java.util.*;

import com.spring.project.Shared.Dto.UserDto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	List<UserDto> getUsers(int page, int limit);

	UserDto getUserByEmail(String email);

	UserDto getUserById(String userid);

	UserDto deleteUserById(String userid);
	
	UserDto deleteUserByEmail(String email);

	UserDto updateUserByEmail(String email, UserDto requestedUpdate);

	UserDto updateUserById(String userid, UserDto requestedUpdate);

}
