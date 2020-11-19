package com.wowgarcon.todo.service;

import com.wowgarcon.todo.domain.TodoDAO;

public interface DataBaseService {
	
    public long generateSequence(String seqName);
    public boolean updateData(TodoDAO todo);

}
