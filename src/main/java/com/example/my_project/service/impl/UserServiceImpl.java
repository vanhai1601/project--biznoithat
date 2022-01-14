package com.example.my_project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.my_project.entity.User;
import com.example.my_project.repository.UserRepository;
import com.example.my_project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public void insert(User u) {
		userRepository.insert(u);

	}

	@Override
	@Transactional
	public void update(User u) {
		userRepository.update(u);

	}

	@Override
	@Transactional
	public void delete(User u) {
		userRepository.delete(u);

	}

	@Override
	@Transactional
	public void save(User u) {
		if (u.getId() > 0) {
			userRepository.update(u);
		} else {
			userRepository.insert(u);
		}
	}

}
