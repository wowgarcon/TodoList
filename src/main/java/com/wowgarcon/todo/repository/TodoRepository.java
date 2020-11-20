package com.wowgarcon.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wowgarcon.todo.domain.TodoDAO;

public interface TodoRepository extends MongoRepository<TodoDAO, Long>{
	
}
