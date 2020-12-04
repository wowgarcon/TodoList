package com.wowgarcon.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wowgarcon.todo.domain.UserDAO;

public interface UserRepository extends MongoRepository<UserDAO, String>{
	public UserDAO findByUsername(String username);
	public int countByUsername(String username);
}
