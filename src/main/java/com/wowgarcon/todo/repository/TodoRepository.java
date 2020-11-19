package com.wowgarcon.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wowgarcon.todo.domain.TodoDAO;

public interface TodoRepository extends MongoRepository<TodoDAO, Long>{
}
