package com.example.my_project.service;

import java.util.List;

import com.example.my_project.entity.User;

public interface UserService {
	List<User> findAll();

	User findById(long id);

	User findByUsername(String username);

	void insert(User u);

	void update(User u);

	void delete(User u);
	
	void save(User u);
}
