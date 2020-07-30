package com.spring.project.Services.Implementations;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.spring.project.Model.User;
import com.spring.project.Model.Response.UserResponse;
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
		
		User newUser = new User();
		BeanUtils.copyProperties(userDto, newUser);
		
		newUser.setEncryptedPassword("test");
		newUser.setEmailVerificationStatus(true);
		newUser.setUserId(utils.generateUserId(30));
		
		UserDto returnValue = new UserDto();
		
		try {
			
			if(userRepository.findByEmail(newUser.getEmail()) == null) {
				
				User storedUserDetails = userRepository.save(newUser);
				
				
				BeanUtils.copyProperties(storedUserDetails, returnValue);
				
				return returnValue;
			}
			else {
				
				throw new Exception(); // Placeholder for custom exceptions
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}

	@Override
	public List<UserDto> getUsers() {

		List<User> userList = new ArrayList<User>();
		userList = (List<User>) userRepository.findAll();
		
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
		
		try {
			
			User foundUser = userRepository.findByEmail(email);
			
			
			if(foundUser == null) {
				throw new Exception(); // Placeholder for custom exceptions
			}
			else {
						
				BeanUtils.copyProperties(foundUser, foundDtoUser);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return foundDtoUser;		
	}

	@Override
	public UserDto getUserById(String userid) {

		UserDto foundDtoUser = new UserDto();
		
		try {
			
			User foundUser = userRepository.findByUserId(userid);
			
			
			if(foundUser == null) {
				throw new Exception(); // Placeholder for custom exceptions
			}
			else {
						
				BeanUtils.copyProperties(foundUser, foundDtoUser);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return foundDtoUser;
	}

	@Override
	public UserDto updateUserById(String userid, UserDto requestedUpdate) {
		
		User updateUser = new User();
		BeanUtils.copyProperties(requestedUpdate, updateUser);
		
		User oldUserData = userRepository.findByUserId(userid);
		
		UserDto returnValue = new UserDto();
		
		try {
			
			if(oldUserData == null) {
				
				throw new Exception(); // Placeholder for custom exceptions
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	@Override
	public UserDto updateUserByEmail(String email, UserDto requestedUpdate) {
		
		User updateUser = new User();
		BeanUtils.copyProperties(requestedUpdate, updateUser);
		
		User oldUserData = userRepository.findByEmail(email);
		
		UserDto returnValue = new UserDto();
		
		try {
			
			if(oldUserData == null) {
				
				throw new Exception(); // Placeholder for custom exceptions
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	@Override
	public UserDto deleteUserById(String userid) {

		UserDto foundDtoUser = new UserDto();
		
		try {
			
			User foundUser = userRepository.findByUserId(userid);
			
			if(foundUser == null) {
				throw new Exception(); // Placeholder for custom exceptions
			}
			else {
						
				BeanUtils.copyProperties(foundUser, foundDtoUser);
				userRepository.deleteById(foundUser.getId());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return foundDtoUser;
	}
	
	@Override
	public UserDto deleteUserByEmail(String email) {
		
		UserDto foundDtoUser = new UserDto();
		
		try {
			
			User foundUser = userRepository.findByEmail(email);
			
			if(foundUser == null) {
				throw new Exception(); // Placeholder for custom exceptions
			}
			else {
						
				BeanUtils.copyProperties(foundUser, foundDtoUser);
				userRepository.deleteById(foundUser.getId());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return foundDtoUser;
	}

}
