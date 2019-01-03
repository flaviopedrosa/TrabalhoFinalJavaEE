package com.javaee.restswagger.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
@AllArgsConstructor
public class Acao {
	public Acao() {
		id = UUID.randomUUID().toString();
	}
	private String id;
    
	@DBRef
	private Empresa empresa;
	@DBRef
	private Pessoa comprador;
	@DBRef
	private Pessoa vendedor;
	private Date dataCompra;
	private float valorCompra;

}
