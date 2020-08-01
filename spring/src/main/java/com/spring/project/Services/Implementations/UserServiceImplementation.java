package com.spring.project.Services.Implementations;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.project.Exceptions.*;
import com.spring.project.Model.User;
import com.spring.project.Services.UserService;
import com.spring.project.Shared.Dto.UserDto.UserDto;
import com.spring.project.Shared.Utils.Utils;
import com.spring.project.dao.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	private final UserRepository userRepository;
	private final Utils utils;
	
	public UserServiceImplementation(UserRepository userRepository, Utils utils) {
		this.userRepository = userRepository;
		this.utils = utils;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		if(userDto.getEmail() == null)
			throw new UnableToCreateUserException("Email required, yet not provided.");
		
		if(userDto.getPassword() == null)
			throw new UnableToCreateUserException("Password required, yet not provided.");
		
		User newUser = new User();
		BeanUtils.copyProperties(userDto, newUser);
		
		newUser.setEncryptedPassword("test");
		newUser.setEmailVerificationStatus(true);
		newUser.setUserId(utils.generateUserId(30));
		
		UserDto returnValue = new UserDto();
		
			
		if(userRepository.findByEmail(newUser.getEmail()) == null) {
			
			User storedUserDetails = userRepository.save(newUser);
			
			
			BeanUtils.copyProperties(storedUserDetails, returnValue);
			
			return returnValue;
		}
		else {
			
			throw new UserAlreadyExistsException("Could not create user. User Already Exists."); // Placeholder for custom exceptions
		}

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		if(page > 0) page --;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<User> userPage = userRepository.findAll(pageableRequest);
		
		List<User> userList = new ArrayList<User>();
		userList = userPage.getContent(); // get content returns a list
		
		List<UserDto> dtoList = new ArrayList<UserDto>();
		
		for(int i=0; i<userList.size(); i++) {
			dtoList.add(new UserDto());
		}
		
		for(int i=0; i<userList.size(); i++) {
			BeanUtils.copyProperties(userList.get(i), dtoList.get(i));
		}
		
		return dtoList;
	}

	@Override
	public UserDto getUserByEmail(String email) {

		UserDto foundDtoUser = new UserDto();

		User foundUser = userRepository.findByEmail(email);
		
		
		if(foundUser == null) {
			throw new UserNotFoundException("User Not Found");
		}
		else {
					
			BeanUtils.copyProperties(foundUser, foundDtoUser);
			return foundDtoUser;
		}

	}

	@Override
	public UserDto getUserById(String userid) {

		UserDto foundDtoUser = new UserDto();

			
		User foundUser = userRepository.findByUserId(userid);
		
		
		if(foundUser == null) {
			throw new UserNotFoundException("User Not Found");
		}
		else {
					
			BeanUtils.copyProperties(foundUser, foundDtoUser);
			return foundDtoUser;
		}
	
	}

	// add error handling if they try to update with an email that already exists. use http code 409
	@Override
	public UserDto updateUserById(String userid, UserDto requestedUpdate) {
		
		User updateUser = new User();
		BeanUtils.copyProperties(requestedUpdate, updateUser);
		
		User oldUserData = userRepository.findByUserId(userid);
		
		UserDto returnValue = new UserDto();
		
		// If requested email is already in use, error out and dont allow the update
		if(userRepository.findByEmail(updateUser.getEmail()) != null)
			throw new RequestedEmailAlreadyInUseException("Unable to perform update. Requested email is already in use.");

			
		if(oldUserData == null) {
			
			throw new UserNotFoundException("User Not Found");
		}
		else {
			
			updateUser.setId(oldUserData.getId());
			updateUser.setUserId(oldUserData.getUserId());
			updateUser.setEncryptedPassword("test1");
			updateUser.setEmailVerificationStatus(oldUserData.isEmailVerificationStatus());
			
			User updatedUserDetails = userRepository.save(updateUser);
			
			
			BeanUtils.copyProperties(updatedUserDetails, returnValue);
			
			return returnValue;
		}

	}
	
	// add error handling if they try to update with an email that already exists. use http code 409
	@Override
	public UserDto updateUserByEmail(String email, UserDto requestedUpdate) {
		
		User updateUser = new User();
		BeanUtils.copyProperties(requestedUpdate, updateUser);
		
		User oldUserData = userRepository.findByEmail(email);
		
		UserDto returnValue = new UserDto();

		// If requested email is already in use, error out and dont allow the update
		if(userRepository.findByEmail(updateUser.getEmail()) != null)
			throw new RequestedEmailAlreadyInUseException("Unable to perform update. Requested email is already in use.");
			
		if(oldUserData == null) {
			
			throw new UserNotFoundException("User Not Found");
		}
		else {
			
			updateUser.setId(oldUserData.getId());
			updateUser.setUserId(oldUserData.getUserId());
			updateUser.setEncryptedPassword("test1");
			updateUser.setEmailVerificationStatus(oldUserData.isEmailVerificationStatus());
			
			User updatedUserDetails = userRepository.save(updateUser);
			
			
			BeanUtils.copyProperties(updatedUserDetails, returnValue);
			
			return returnValue;
		}

	}
	
	@Override
	public UserDto deleteUserById(String userid) {

		UserDto foundDtoUser = new UserDto();
		
		User foundUser = userRepository.findByUserId(userid);
		
		if(foundUser == null) {
			throw new  UserNotFoundException("User Not Found");
		}
		else {
					
			BeanUtils.copyProperties(foundUser, foundDtoUser);
			userRepository.deleteById(foundUser.getId());
			
			return foundDtoUser;
		}
		
	}
	
	@Override
	public UserDto deleteUserByEmail(String email) {
		
		UserDto foundDtoUser = new UserDto();

		User foundUser = userRepository.findByEmail(email);
		
		if(foundUser == null) {
			throw new  UserNotFoundException("User Not Found");
		}
		else {
					
			BeanUtils.copyProperties(foundUser, foundDtoUser);
			userRepository.deleteById(foundUser.getId());
			
			return foundDtoUser;
		}

	}

}
