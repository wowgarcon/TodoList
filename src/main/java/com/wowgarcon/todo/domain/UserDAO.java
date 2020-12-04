package com.wowgarcon.todo.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Document(collection = "user")
@Data
public class UserDAO{
	
	//dabatase_sequences 컬렉션의 시퀀스 컬럼명
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String createDate;
	
}
