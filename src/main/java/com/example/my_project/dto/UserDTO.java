package com.example.my_project.dto;

import java.io.Serializable;
import java.util.List;

import com.example.my_project.entity.Role;

public class UserDTO implements Serializable {
	private long id;
	private String username;
	private String email;
	private String phone;
	private String password;
	private String address;
	private Boolean active;
	private List<Role> roles;
	
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
