package com.wowgarcon.todo.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document(collection = "todolist")
@Data
public class TodoDAO{
	
	//dabatase_sequences 컬렉션의 시퀀스 컬럼명
	@Transient
    public static final String SEQUENCE_NAME = "todos_sequence";
	
	@Id
	private long id;
	private String userId;
	private String todoContent;
	private String createDate;
}
