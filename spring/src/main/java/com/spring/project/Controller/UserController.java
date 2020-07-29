package com.spring.project.Controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.spring.project.Model.User;
import com.spring.project.Services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getUsers() {
		
		List<User> returnValue = userService.getUsers();
		
		return returnValue;
	}
	
	@GetMapping(path = "/email={email}")
	public User getUser(@PathVariable String email) {
		
		User returnValue = userService.getUser(email);
		
		return returnValue;
	}
	
	@GetMapping(path = "/id={id}")
	public Optional<User> getUser(@PathVariable Long id) {
		
		Optional<User> returnValue = userService.getUser(id);
		
		return returnValue;
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		
		userService.createUser(user);
	}
	
	@PutMapping(path = "email={email}")
	public User updateUser(@PathVariable String email, @RequestBody User user) {

		User returnValue = userService.updateUser(email, user);
		return returnValue;
	}
	
	@PutMapping(path = "id={id}")
	public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {

		Optional<User> returnValue = userService.updateUser(id, user);
		return returnValue;
	}
	
	@DeleteMapping(path = "/id={id}")
	public Optional<User> deleteUser(@PathVariable Long id) {
		
		Optional<User> returnValue = userService.deleteUser(id);
		return returnValue;
	}
	
	@DeleteMapping(path = "/email={email}")
	public User deleteUser(@PathVariable String email) {
		
		User returnValue = userService.deleteUser(email);
		return returnValue;
	}
	
}
