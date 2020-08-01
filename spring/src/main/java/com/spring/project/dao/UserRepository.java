package com.spring.project.dao;

import org.springframework.stereotype.Repository;

import com.spring.project.Model.User;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(String email);
	User findByUserId(String userid);
}
