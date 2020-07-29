package com.spring.project.Services;

import java.util.*;
import com.spring.project.Model.User;

public interface UserService {
	List<User> getUsers();

	void createUser(User user);

	User getUser(String email);

	Optional<User> getUser(Long id);

	Optional<User> updateUser(Long id, User user);
	
	User updateUser(String email, User user);
	
	Optional<User> deleteUser(Long id);

	User deleteUser(String email);

}
