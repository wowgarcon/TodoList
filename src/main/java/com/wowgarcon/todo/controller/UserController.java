package com.wowgarcon.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.wowgarcon.todo.domain.TodoDAO;
import com.wowgarcon.todo.domain.UserDAO;
import com.wowgarcon.todo.repository.TodoRepository;

import lombok.extern.java.Log;

@RestController
@Log
public class UserController {

	//@Autowired
	//private ApiServiceImpl apiService;
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping("/signUp")
	public ResponseEntity<Map<String, UserDAO>> signUp(UserDAO user) {
		Map<String, UserDAO> map = new HashMap<String, UserDAO>();
		map.put("result", user);
		return new ResponseEntity<Map<String, UserDAO>>(map, HttpStatus.OK);
	}
	
}
