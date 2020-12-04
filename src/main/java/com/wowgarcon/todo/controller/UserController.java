package com.wowgarcon.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wowgarcon.todo.domain.UserDAO;
import com.wowgarcon.todo.domain.UtilDTO;
import com.wowgarcon.todo.repository.UserRepository;
import com.wowgarcon.todo.service.DataBaseService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/*")
@Log
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DataBaseService dataBaseService;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private UtilDTO util = new UtilDTO();
	
	@PostMapping("/join")
	public Integer signUp(UserDAO user){
		int count = userRepository.countByUsername(user.getUsername());
		if(count > 0){ return 409; }
		
		user.setId(dataBaseService.generateSequence(user.SEQUENCE_NAME));
		String rawPw = user.getPassword();
		String encPw = passwordEncoder.encode(rawPw);
		user.setPassword("{noop}"+encPw);
		user.setRole("ROLE_USER");
		user.setCreateDate(util.getCurrentDate());
		
		userRepository.save(user);
		
		return 200;
	}
	
//	@PostMapping("/loginProcess")
//	public String login(){
//		System.out.println("login");
//		return "success";
//	}
//	
}
