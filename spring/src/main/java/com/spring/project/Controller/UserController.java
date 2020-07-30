package com.spring.project.Controller;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.spring.project.Model.User;
import com.spring.project.Model.Request.UserRequest;
import com.spring.project.Model.Response.UserResponse;
import com.spring.project.Services.UserService;
import com.spring.project.Shared.Dto.UserDto.UserDto;

@RestController
@RequestMapping(path = "users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
	)	
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@GetMapping
	public List<UserResponse> getUsers() {
		
		List<UserDto> dtoList = userService.getUsers();
		
		List<UserResponse> returnValue = new ArrayList<UserResponse>();
		
		// initialize empty response list to copy values into
		for(int i=0; i<dtoList.size(); i++) {
			returnValue.add(new UserResponse());
		}
		
		for(int i=0; i<dtoList.size(); i++) {
			BeanUtils.copyProperties(dtoList.get(i), returnValue.get(i));
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/email={email}")
	public UserResponse getUserByEmail(@PathVariable String email) {
		
		UserDto obtainedUser = userService.getUserByEmail(email);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(obtainedUser, returnValue);
		
		return returnValue;
	}
	
	@GetMapping(path = "/userid={userid}")
	public UserResponse getUserById(@PathVariable String userid) {
		
		UserDto obtainedUser = userService.getUserById(userid);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(obtainedUser, returnValue);
		
		return returnValue;
	}
	
//	@PutMapping(path = "email={email}")
//	public User updateUser(@PathVariable String email, @RequestBody User user) {
//
//		User returnValue = userService.updateUser(email, user);
//		return returnValue;
//	}
//	
//	@PutMapping(path = "/userid={userid}")
//	public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//
//		Optional<User> returnValue = userService.updateUser(id, user);
//		return returnValue;
//	}
	
//	@DeleteMapping(path = "/userid={userid}")
//	public Optional<User> deleteUser(@PathVariable Long id) {
//		
//		Optional<User> returnValue = userService.deleteUser(id);
//		return returnValue;
//	}
	
	@DeleteMapping(path = "/email={email}")
	public UserResponse deleteUser(@PathVariable String email) {
		
		UserDto deletedUser = userService.deleteUser(email);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(deletedUser, returnValue);
		
		return returnValue;
	}
	
}
