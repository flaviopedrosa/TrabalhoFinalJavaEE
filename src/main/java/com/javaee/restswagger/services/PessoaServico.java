package com.javaee.restswagger.services;

import java.util.Set;
import com.javaee.restswagger.domain.Pessoa;


public interface PessoaServico {
	Set<Pessoa> getAll();

	Pessoa getPessoaById(String id);

	Pessoa criarNovaPessoa(Pessoa pessoa);

	Pessoa salvarPessoa(String id, Pessoa pessoa);

	void deletePessoaById(String id);
}