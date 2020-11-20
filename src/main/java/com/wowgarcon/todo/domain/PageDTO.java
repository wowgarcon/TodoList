package com.wowgarcon.todo.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.Data;

@Data
public class PageDTO {
	private String page;
	
	//include할 페이지 생성자
	public PageDTO() {
		page = "welcome";
	}
	
	
}
