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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wowgarcon.todo.domain.TodoDAO;
import com.wowgarcon.todo.repository.TodoRepository;
import com.wowgarcon.todo.service.DataBaseService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/*")
@Log
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private DataBaseService dataBaseService;
	
	//가져오기
	@GetMapping("/todolist")
	public ResponseEntity<List<TodoDAO>> getTodo() {
		List<TodoDAO> todoList = todoRepository.findAll();
		return new ResponseEntity<>(todoList, HttpStatus.OK);
	}
	
	//추가하기
	@PostMapping("/todo")
	public ResponseEntity<TodoDAO> addTodo(TodoDAO todo) {
		try {
			todo.setId(dataBaseService.generateSequence(todo.SEQUENCE_NAME));
			todoRepository.save(todo);
			return new ResponseEntity<>(todo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//리턴값 파라미터로 받아서 한번에 처리
		//exception의 커스터마이징 처리 공부
	}
	
	
	//수정
	@PutMapping("/todo")
	public ResponseEntity<TodoDAO> getTodo(TodoDAO todo) {
		try {
			//업데이트시 0,1의 인트값으로 받기
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
	@DeleteMapping("/todo")
	public ResponseEntity<String> deleteTodo(TodoDAO todo) {
		//httpstatus에서 제공하는 코드의 한계
		//제안 시 제공할 연동규격서 상에서 다양한 에러의 표현을 위해 
		try {
			todoRepository.deleteById(todo.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
