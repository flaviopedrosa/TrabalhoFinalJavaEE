package com.javaee.restswagger.services;

import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.domain.Acao;

public interface EnvioEmail {
	void enviarAvisoDePedidoDeCompra(Empresa empresa, Pessoa comprador, Pessoa vendedor);
	void enviarAvisoDeFinalizacaoDeCompraComSucesso(Empresa empresa, Pessoa comprador, Pessoa vendedor,  Acao acao);
	void enviarAvisoDeFinalizacaoDeCompraComErro(Pessoa comprador, String motivo,  Pessoa Vendedor);
}
