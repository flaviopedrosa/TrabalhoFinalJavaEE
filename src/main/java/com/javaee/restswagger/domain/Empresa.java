package com.javaee.restswagger.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Empresa extends Pessoa{
	
	public Empresa() {
	}
	private int numeroAcoesMaximoParaVenda;
	private int numeroAcoes;
	private float valorAcao;
}
