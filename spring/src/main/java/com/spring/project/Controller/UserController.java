package com.spring.project.Controller;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
	public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
									   @RequestParam(value = "limit", defaultValue = "3") int limit) {
		
		List<UserDto> dtoList = userService.getUsers(page, limit);
		
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
	
	@PutMapping(path = "email={email}")
	public UserResponse updateUserByEmail(@PathVariable String email, @RequestBody UserRequest userRequest) {

		UserDto requestedUpdate = new UserDto();
		BeanUtils.copyProperties(userRequest, requestedUpdate);
		
		UserDto updatedUser = userService.updateUserByEmail(email, requestedUpdate);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path = "/userid={userid}")
	public UserResponse updateUserById(@PathVariable String userid, @RequestBody UserRequest userRequest) {

		UserDto requestedUpdate = new UserDto();
		BeanUtils.copyProperties(userRequest, requestedUpdate);
		
		UserDto updatedUser = userService.updateUserById(userid, requestedUpdate);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path = "/userid={userid}")
	public UserResponse deleteUserById(@PathVariable String userid) {
		
		UserDto deletedUser = userService.deleteUserById(userid);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(deletedUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path = "/email={email}")
	public UserResponse deleteUserByEmail(@PathVariable String email) {
		
		UserDto deletedUser = userService.deleteUserByEmail(email);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(deletedUser, returnValue);
		
		return returnValue;
	}
	
}
