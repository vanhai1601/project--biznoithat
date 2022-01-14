package com.example.my_project.repository;

import java.util.List;

import com.example.my_project.entity.User;

public interface UserRepository {
	List<User> findAll();
	
	User findByUsername(String username);
	

	User findById(long id);

	void insert(User u);

	void update(User u);

	void delete(User u);

}
