package com.spring.project.Services;

import java.util.*;


import com.spring.project.Model.User;
import com.spring.project.Model.Response.UserResponse;
import com.spring.project.Shared.Dto.UserDto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	List<UserDto> getUsers();

	UserDto getUserByEmail(String email);

	UserDto getUserById(String userid);

	UserDto deleteUser(String email);

//	Optional<User> updateUser(Long id, User user);
//	
//	User updateUser(String email, User user);
	
//	Optional<User> deleteUser(Long id);

}
