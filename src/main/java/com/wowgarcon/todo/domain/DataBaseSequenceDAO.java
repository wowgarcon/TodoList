package com.wowgarcon.todo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "dabatase_sequences")
@Data
public class DataBaseSequenceDAO {
	
		@Id
		private String id;
		private long seq;
		
}
