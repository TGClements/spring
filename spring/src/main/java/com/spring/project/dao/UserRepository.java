package com.spring.project.dao;

import org.springframework.stereotype.Repository;

import com.spring.project.Model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	User findByUserId(String userid);
}
