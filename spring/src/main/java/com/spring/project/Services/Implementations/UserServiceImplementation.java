package com.spring.project.Services.Implementations;

import java.util.*;
import org.springframework.stereotype.Service;
import com.spring.project.Model.User;
import com.spring.project.Services.UserService;
import com.spring.project.dao.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	private final UserRepository userRepository;
	
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUsers() {
		
		List<User> returnValue = new ArrayList<User>();
		
		returnValue = (List<User>) userRepository.findAll();
		
		return returnValue;
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User getUser(String email) {

		User returnValue = userRepository.findByEmail(email);
		
		return returnValue;
	}

	@Override
	public Optional<User> getUser(Long id) {
		
		Optional<User> returnValue = userRepository.findById(id);
		
		return returnValue;
	}
	
	@Override
	public Optional<User> updateUser(Long id, User user) {
		
		Optional<User> opOldUser = userRepository.findById(id);
		User updatedUser = opOldUser.get();
		
		updatedUser.setEmail(user.getEmail());
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		
		userRepository.save(updatedUser);

		return opOldUser;
	}
	
	@Override
	public User updateUser(String email, User user) {

		User opOldUser = userRepository.findByEmail(email);
		User updatedUser = opOldUser;
		
		updatedUser.setEmail(user.getEmail());
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		
		userRepository.save(updatedUser);

		return opOldUser;
	}

	@Override
	public Optional<User> deleteUser(Long id) {
		
		Optional<User> returnValue = userRepository.findById(id);
		userRepository.deleteById(id);
		
		return returnValue;
	}

	@Override
	public User deleteUser(String email) {

		User returnValue = userRepository.findByEmail(email);
		userRepository.deleteById(returnValue.getId());
		
		return returnValue;
	}
	
}
