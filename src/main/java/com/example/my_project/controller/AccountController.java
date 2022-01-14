package com.example.my_project.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_project.dto.AuthenticationRequestDTO;
import com.example.my_project.dto.JWTResponseDTO;
import com.example.my_project.dto.UserDTO;
import com.example.my_project.entity.User;
import com.example.my_project.jwt.JWTTokenComponent;
import com.example.my_project.jwt.JWTUserDetailsService;
import com.example.my_project.service.UserService;
import com.example.my_project.tranform.UserTranform;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accounts")
public class AccountController {

	private UserService userService;
	private AuthenticationManager authenticationManager;
	private JWTTokenComponent jwtTokenComponent;
	private JWTUserDetailsService jwtUserDetailsService;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public AccountController(UserService userService,BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JWTTokenComponent jwtTokenComponent, JWTUserDetailsService jwtUserDetailsService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenComponent = jwtTokenComponent;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JWTResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO dto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(dto.getUsername());
		String token = jwtTokenComponent.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponseDTO(token));
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO body) throws ParseException {
		UserTranform userTranform = new UserTranform();
		User user = userTranform.apply(body);
		encryptPassword(user);
		userService.save(user);
		return ResponseEntity.ok(userTranform.apply(user));
	}
	
	@GetMapping()
	@Secured("ROLE_ADMIN")
	public List<UserDTO> allUsers() {
		UserTranform userTranform = new UserTranform();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		List<User> users = userService.findAll();
		for (User u : users) {
			UserDTO userDTO = userTranform.apply(u);
			userDTOs.add(userDTO);
		}
		return userDTOs;

	}
	
	@PostMapping("/detail")
	public UserDTO findUser(@RequestBody UserDTO username) {
		UserTranform userTranform = new UserTranform();
		User user = userService.findByUsername(username.getUsername());
		UserDTO userDTO = userTranform.apply(user);
		return userDTO;
	}

	private void encryptPassword(User user) {
		String rawPassword = user.getPassword();
		if (rawPassword != null) {
			user.setPassword(passwordEncoder.encode(rawPassword));
		}
	}
	
}
