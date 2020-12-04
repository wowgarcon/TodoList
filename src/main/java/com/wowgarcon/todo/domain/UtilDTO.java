package com.wowgarcon.todo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class UtilDTO{
	
	//시간 구하기
	public String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String currentTime = formatter.format(date);
		return currentTime;
	}
}
