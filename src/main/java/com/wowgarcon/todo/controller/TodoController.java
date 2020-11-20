package com.wowgarcon.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wowgarcon.todo.domain.TodoDAO;
import com.wowgarcon.todo.domain.UserDAO;
import com.wowgarcon.todo.repository.TodoRepository;
import com.wowgarcon.todo.service.DataBaseService;

import lombok.extern.java.Log;

@RestController
@Log
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private DataBaseService dataBaseService;
	
	@PostMapping("/todoList")
	public ResponseEntity<List<TodoDAO>> getTodo() {
		List<TodoDAO> todoList = todoRepository.findAll();
		return new ResponseEntity<>(todoList, HttpStatus.OK);
	}
	//추가하기
	@PostMapping("/todo")
	public ResponseEntity<TodoDAO> addTodo(TodoDAO todo) {
		try {
			todo.setId(dataBaseService.generateSequence(todo.SEQUENCE_NAME));
			todo.setCreateTime();
			todoRepository.save(todo);
			return new ResponseEntity<>(todo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//가져오기
	@GetMapping("/todos")
	public List<TodoDAO> getTodos() {
		return todoRepository.findAll();
	}
	
	//수정
	@PutMapping("/todo")
	public ResponseEntity<TodoDAO> getTodo(TodoDAO todo) {
		try {
			boolean updateResult = dataBaseService.updateData(todo);
			if(updateResult) {
				return new ResponseEntity<>(todo, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(todo, HttpStatus.NOT_FOUND);
		}
	}
	
	//삭제
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable long id) {
		try {
			todoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
