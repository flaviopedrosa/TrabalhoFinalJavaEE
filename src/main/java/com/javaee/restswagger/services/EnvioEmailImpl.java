package com.javaee.restswagger.services;

import com.javaee.restswagger.config.EmailConfig;
import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.domain.Acao;

public class EnvioEmailImpl implements EnvioEmail{

	final String remetente = "flaviopedrosa@gmail.com";
	final String senha = "Logomarc@170185#";
	final String tituloAviso ="";
	final String tituloCompraSucesso = "";
	final String tituloCompraCancelada = "";
	
	private static EmailConfig config = new EmailConfig();

	@Override
	public void enviarAvisoDePedidoDeCompra(Empresa empresa, Pessoa comprador, Pessoa vendedor) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Sua transação esta sendo processada.");
		buffer.append("<br/>");
		buffer.append("<br/>");
		buffer.append("Ações da Empresa: ");
		buffer.append(empresa.getNome());
		buffer.append("<br/>");
		buffer.append("Valor da Ação: " );
		buffer.append(empresa.getValorAcao());
		buffer.append("<br/>");
		
		enviar(buffer.toString(), tituloAviso, comprador.getEmail());
		enviar(buffer.toString(), tituloAviso, vendedor.getEmail());
	}

	@Override
	public void enviarAvisoDeFinalizacaoDeCompraComSucesso(Empresa empresa, Pessoa comprador, Pessoa vendedor,  Acao acao) {
		// TODO Auto-generated method stub
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("Sua transação foi finalizada com sucesso.");
		buffer.append("<br/>");
		buffer.append("<br/>");
		buffer.append("Ações da Empresa: ");
		buffer.append(empresa.getNome());
		buffer.append("<br/>");
		buffer.append("Valor da Ação: " );
		buffer.append(empresa.getValorAcao());
		buffer.append("<br/>");
		buffer.append("Transação: ");
		buffer.append(acao.getId());
		buffer.append("<br/>");
		enviar(buffer.toString(), tituloCompraSucesso, comprador.getEmail());
		enviar(buffer.toString(), tituloCompraSucesso, vendedor.getEmail());
	}

	@Override
	public void enviarAvisoDeFinalizacaoDeCompraComErro(Pessoa comprador, String motivo, Pessoa Vendedor ) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Infelizmente não conseguimos concluir sua transação.");
		buffer.append("<br/>");
		buffer.append("Causa: ");
		buffer.append(motivo);
		enviar(buffer.toString(), tituloCompraCancelada, comprador.getEmail());
		enviar(buffer.toString(), tituloCompraCancelada, Vendedor.getEmail());
	}
	
	private void enviar(String texto, String assunto, String destinatario) {
		config.sendEmail(remetente, senha, destinatario, assunto, texto);
	}
}
