package com.example.my_project.tranform;

import com.example.my_project.dto.UserDTO;
import com.example.my_project.entity.User;

public class UserTranform {
	private RoleTransform roleTransform;
	
	public UserTranform() {
		roleTransform = new RoleTransform();
	}
	
	public User apply(UserDTO dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPhone(dto.getPhone());
		user.setPassword(dto.getPassword());
		user.setAddress(dto.getAddress());
		user.setActive(true);
		user.setRoles(1);
		return user;
	}

	public UserDTO apply(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		dto.setPassword(user.getPassword());
		dto.setPhone(user.getPhone());
		dto.setRoles(roleTransform.apply(user.getRoles()));
		dto.setActive(user.isActive());
		dto.setId(user.getId());

		return dto;

	}
}
