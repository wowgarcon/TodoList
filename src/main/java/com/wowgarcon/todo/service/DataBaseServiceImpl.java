package com.wowgarcon.todo.service;

import com.wowgarcon.todo.domain.DataBaseSequenceDAO;
import com.wowgarcon.todo.domain.TodoDAO;
import com.wowgarcon.todo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class DataBaseServiceImpl implements DataBaseService {

	@Autowired
	private TodoRepository todoRepository;
	private MongoOperations mongoOperations; //시퀀스 생성용으로 여러번 재사용하기 위해 선언 
	
	@Autowired
	public DataBaseServiceImpl(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	@Override
	public long generateSequence(String seqName) {
		DataBaseSequenceDAO counter = mongoOperations.findAndModify(
				new Query(new Criteria("_id").is(seqName)), 
				new Update().inc("seq",1), 
				new FindAndModifyOptions().returnNew(true).upsert(true),
				DataBaseSequenceDAO.class);
		
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

	@Override
	public boolean updateData(TodoDAO todo) {
		try {
		    TodoDAO originalTodo = todoRepository.findById(todo.getId());
		    originalTodo.setUserId(todo.getUserId());
		    originalTodo.setTodoContent(todo.getTodoContent());
		    todoRepository.save(originalTodo);
			return true;
		} catch (Exception e) {
			return false;
		}

	}	
}
