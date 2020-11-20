package com.wowgarcon.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wowgarcon.todo.domain.UserDAO;
import com.wowgarcon.todo.repository.UserRepository;

import lombok.extern.java.Log;

@RestController
@Log
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/join")
	public ResponseEntity<UserDAO> signUp(UserDAO user) {
		System.out.println(user.getCreateDate());
		user.setCreateDate();
		
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		System.out.println(user.getUserPw());
		System.out.println(user.getCreateDate());
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
