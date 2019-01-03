package com.javaee.restswagger.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
@AllArgsConstructor
public class PreTransacao {
	
	@Id
	private String id;
	private String idComprador;
	private String idVendedor;
	private String idEmpresa;
}
