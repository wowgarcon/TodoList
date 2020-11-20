package com.wowgarcon.todo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "user")
@Data
public class UserDAO{
	@Id
	private String userId;
	private String userName;
	private String userPw;
	private String createDate;
	
	public void setCreateDate() {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String currentTime = formatter.format(date);
		this.createDate = currentTime;
	}
}
