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

		private MongoOperations mongoOperations;

		@Autowired
		public DataBaseServiceImpl(MongoOperations mongoOperations) {
			this.mongoOperations = mongoOperations;
		}
		
		@Autowired
		private TodoRepository todoRepository;
		
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
			    TodoDAO originalTodo = mongoOperations.findById(todo.getId(), TodoDAO.class);
			    System.out.println(todoRepository.findById(todo.getId()));
			    originalTodo.setUserId(todo.getUserId());
			    originalTodo.setTodoContent(todo.getTodoContent());
			    mongoOperations.save(originalTodo);
				return true;
			} catch (Exception e) {
				return false;
			}

		}
	
}
