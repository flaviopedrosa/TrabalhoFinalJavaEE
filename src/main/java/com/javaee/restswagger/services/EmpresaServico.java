package com.javaee.restswagger.services;

import java.util.Set;
import com.javaee.restswagger.domain.Empresa;


public interface EmpresaServico {
	Set<Empresa> getAll();

	Empresa getEmpresaById(String id);

	Empresa criarNovaEmpresa(Empresa empresa);

	Empresa salvarEmpresa(String id, Empresa empresa);
	
	float ajustarValorDaAcao(float percentual, String idEmpresa);
	
	int aumentarNumeroDeAcoes(int aumento, String idEmpresa);

	void deleteEmpresaById(String id);
}
