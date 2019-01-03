package com.javaee.restswagger.services;

import java.util.Set;
import com.javaee.restswagger.domain.Acao;

public interface AcaoServico {
	String comprarAcao(String idComprador, String idVendedor, String idEmpresa);
	Set<Acao> AcoesDeUmaPessoa(String idComprador);
	Set<Acao> AcoesDeUmaPessoaDeUmaEmpresa(String idComprador, String idEmpresa);
}
