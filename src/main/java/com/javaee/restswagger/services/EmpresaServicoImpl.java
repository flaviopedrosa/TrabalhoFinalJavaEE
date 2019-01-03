package com.javaee.restswagger.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.repository.EmpresaRepositorio;

@Service
public class EmpresaServicoImpl implements EmpresaServico{
	
	private EmpresaRepositorio empresaRepositorio;
	
	public EmpresaServicoImpl(EmpresaRepositorio empresaRepositorio) {
		this.empresaRepositorio = empresaRepositorio;
	}

	@Override
	public Set<Empresa> getAll() {
		Set<Empresa> empresas = new HashSet<>();
		this.empresaRepositorio.findAll().iterator().forEachRemaining(empresas::add);
		return empresas;
	}

	@Override
	public Empresa getEmpresaById(String id) {
		Optional<Empresa> empresa = this.empresaRepositorio.findById(id);
		if(!empresa.isPresent())
			 throw new IllegalArgumentException("Não existe empresa cadastrada com o id: " + id.toString() );
		return empresa.get();
	}

	@Override
	public Empresa criarNovaEmpresa(Empresa empresa) {
		return this.empresaRepositorio.save(empresa);
	}

	@Override
	public Empresa salvarEmpresa(String id, Empresa empresa) {
		empresa.setId(id);
		Empresa empresaInDb = this.empresaRepositorio.save(empresa);
		return empresaInDb;
	}

	@Override
	public float ajustarValorDaAcao(float percentual, String idEmpresa) {
		Empresa empresa = this.getEmpresaById(idEmpresa);
		float novoValor = empresa.getValorAcao() * (percentual + 1);
		empresa.setValorAcao(novoValor);
		this.salvarEmpresa(idEmpresa, empresa);
		return novoValor;
	}

	@Override
	public int aumentarNumeroDeAcoes(int aumento, String idEmpresa) {
		Empresa empresa = this.getEmpresaById(idEmpresa);
		int novoNumeroDeAcoes = empresa.getNumeroAcoes() + aumento;
		int novoNumeroAcoesMaximoParaVenda = empresa.getNumeroAcoesMaximoParaVenda() + aumento;
		empresa.setNumeroAcoes(novoNumeroDeAcoes);
		empresa.setNumeroAcoesMaximoParaVenda(novoNumeroAcoesMaximoParaVenda);
		this.salvarEmpresa(idEmpresa, empresa);
		return novoNumeroDeAcoes;
	}

	@Override
	public void deleteEmpresaById(String id) {
		this.empresaRepositorio.deleteById(id);		
	}
}
