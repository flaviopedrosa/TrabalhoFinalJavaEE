package com.javaee.restswagger.bootstrap;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.javaee.restswagger.domain.Acao;
import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.repository.AcaoRepositorio;
import com.javaee.restswagger.repository.EmpresaRepositorio;
import com.javaee.restswagger.repository.PessoaRepositorio;
import com.javaee.restswagger.services.EnvioEmailImpl;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private PessoaRepositorio pessoaRepositorio;
	private EmpresaRepositorio empresaRepositorio;
	private AcaoRepositorio acaoRepositorio;

	public ApplicationBootstrap(PessoaRepositorio pessoaRepositorio, EmpresaRepositorio empresaRepositorio, AcaoRepositorio acaoRepositorio)
	{
		this.empresaRepositorio = empresaRepositorio;
		this.pessoaRepositorio = pessoaRepositorio;
		this.acaoRepositorio = acaoRepositorio;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		
		pessoaRepositorio.deleteAll();
		empresaRepositorio.deleteAll();
		acaoRepositorio.deleteAll();
		
		carregarPessoas();
		CarregarEmpresas();
		carregarAcoes();
	}
	
	public void carregarPessoas() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId("b9b1d927-5881-4632-9f2c-247dedeae9d1");
		pessoa.setNome("João da silva");
		pessoa.setEmail("flaviopedrosa@gmail.com");
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setId("e4724b43-ff4f-4649-b64e-7c55be76d83a");
		pessoa1.setNome("Marcelo Costa");
		pessoa1.setEmail("pedrosa.flavio@outlook.com");
		
		Set<Pessoa> pessoas = new HashSet<Pessoa>();
		pessoas.add(pessoa);
		pessoas.add(pessoa1);
		
		pessoaRepositorio.saveAll(pessoas);
	}
	public void CarregarEmpresas() {
		
		Empresa empresa = new Empresa();
		empresa.setId("a251218f-d65a-48a9-b0b4-acd51a0eaa9a");
		empresa.setEmail("empresa1@gmail.com");
		empresa.setNome("Empresa um");
		empresa.setNumeroAcoesMaximoParaVenda(99);
		empresa.setNumeroAcoes(200);
		empresa.setValorAcao(199.50f);
		
		Empresa empresa2 = new Empresa();
		empresa2.setId("41a84037-3547-425a-a990-f82727214228");
		empresa2.setEmail("empresa1@gmail.com");
		empresa2.setNome("Empresa dois");
		empresa2.setNumeroAcoesMaximoParaVenda(99);
		empresa2.setNumeroAcoes(200);
		empresa2.setValorAcao(99.50f);
		
		
		Set<Empresa> empresas = new HashSet<Empresa>();
		empresas.add(empresa);
		empresas.add(empresa2);
		
		empresaRepositorio.saveAll(empresas);
		EnvioEmailImpl email = new EnvioEmailImpl();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João da silva");
		pessoa.setEmail("flaviopedrosa@gmail.com");
		email.enviarAvisoDePedidoDeCompra(empresa, pessoa, empresa);
		
	}
	public void carregarAcoes() {
		Acao acao = new Acao();
		Pessoa pessoa = new Pessoa();
		pessoa.setId("b9b1d927-5881-4632-9f2c-247dedeae9d1");
		pessoa.setNome("João da silva");
		pessoa.setEmail("flaviopedrosa@gmail.com");
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setId("e4724b43-ff4f-4649-b64e-7c55be76d83a");
		pessoa1.setNome("Marcelo Costa");
		pessoa1.setEmail("pedrosa.flavio@outlook.com");
		
		Empresa empresa = new Empresa();
		empresa.setId("a251218f-d65a-48a9-b0b4-acd51a0eaa9a");
		empresa.setEmail("empresa1@gmail.com");
		empresa.setNome("Empresa um");
		empresa.setNumeroAcoesMaximoParaVenda(99);
		empresa.setNumeroAcoes(200);
		empresa.setValorAcao(199.50f);
		
		acao.setComprador(pessoa);
		acao.setVendedor(pessoa1);
		acao.setEmpresa(empresa);
		acao.setValorCompra(empresa.getValorAcao());
		GregorianCalendar calendar = new GregorianCalendar();
		acao.setDataCompra(calendar.getTime());
		acaoRepositorio.save(acao);
		
		
	}
}
