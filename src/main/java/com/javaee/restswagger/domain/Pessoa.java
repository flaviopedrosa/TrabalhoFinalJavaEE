package com.javaee.restswagger.domain;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
@AllArgsConstructor
public class Pessoa {
	public Pessoa() {
		id = UUID.randomUUID().toString();
	}
	
	@Id
	private String id;
	private String nome;
	private String email;
}
