package com.javaee.restswagger.services;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.javaee.restswagger.config.RabbitMQConfig;
import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.domain.Acao;
import com.javaee.restswagger.repository.AcaoRepositorio;
import com.javaee.restswagger.repository.EmpresaRepositorio;
import com.javaee.restswagger.repository.PessoaRepositorio;

@Service
public class AcaoServicoImpl implements AcaoServico {

	private final RabbitTemplate rabbitTemplate;
	private final EmpresaRepositorio empresaRepositorio;
	private final PessoaRepositorio pessoaRepositorio;
	private final AcaoRepositorio acaoRepositorio;
	 
    public AcaoServicoImpl(RabbitTemplate rabbitTemplate, EmpresaRepositorio empresaRepositorio, PessoaRepositorio pessoaRepositorio, AcaoRepositorio acaoRepositorio) {
        this.rabbitTemplate = rabbitTemplate;
        this.empresaRepositorio = empresaRepositorio;
        this.pessoaRepositorio = pessoaRepositorio;
        this.acaoRepositorio = acaoRepositorio;
    }
    

	@Override
	public String comprarAcao(String idComprador, String idVendedor, String idEmpresa) {
		// TODO Auto-generated method stub
		GregorianCalendar calendario = new GregorianCalendar();
		StringBuilder erros = new StringBuilder();
		Acao acao = new Acao(); 
		Pessoa empresaVend = new Pessoa();
		Optional<Empresa> empresa = empresaRepositorio.findById(idEmpresa);
		Optional<Pessoa> comprador = pessoaRepositorio.findById(idComprador);
		Optional<Pessoa> vendedor = pessoaRepositorio.findById(idVendedor);
		if(!empresa.isPresent())
			erros.append("Empresa não encontrada");
		if(!comprador.isPresent())
			erros.append("Comprador não encontrado");
		if(!vendedor.isPresent()) {
			Optional<Empresa> empresaVendedora = empresaRepositorio.findById(idVendedor);
			if(!empresaVendedora.isPresent())
				erros.append("Vendedor Não encontrado");
			else {
				empresaVend.setEmail(empresaVendedora.get().getEmail());
				empresaVend.setId(empresaVendedora.get().getId());
				empresaVend.setNome(empresaVendedora.get().getNome());
			}
		}
		
		if(erros.length() > 0)
			return erros.toString();
		
		acao.setComprador(comprador.get());
		acao.setDataCompra(calendario.getTime());
		acao.setEmpresa(empresa.get());
		acao.setValorCompra(empresa.get().getValorAcao());
		if(vendedor.isPresent())
			acao.setVendedor(vendedor.get());
		else
			acao.setVendedor(empresaVend);	
		
		EnvioEmailImpl envioEmailImpl = new EnvioEmailImpl();
		envioEmailImpl.enviarAvisoDePedidoDeCompra(acao.getEmpresa(), acao.getVendedor(), acao.getComprador());
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_MESSAGES, acao);
		
		return  "Compra em processo. Verifique seu email";
	}


	@Override
	public Set<Acao> AcoesDeUmaPessoa(String idComprador) {
		Set<Acao> acoes = new HashSet<>();
		Acao acao = new Acao();
		Pessoa comprador = new Pessoa();
		comprador.setId(idComprador);
		acao.setComprador(comprador);
		Example<Acao> exemplo = Example.of(acao);
		this.acaoRepositorio.findAll(exemplo).iterator().forEachRemaining(acoes::add);
		return acoes;
	}


	@Override
	public Set<Acao> AcoesDeUmaPessoaDeUmaEmpresa(String idComprador, String idEmpresa) {
		Set<Acao> acoes = new HashSet<>();
		Acao acao = new Acao();
		Pessoa comprador = new Pessoa();
		Empresa empresa = new Empresa();
		empresa.setId(idEmpresa);
		comprador.setId(idComprador);
		acao.setComprador(comprador);
		acao.setEmpresa(empresa);
		Example<Acao> exemplo = Example.of(acao);
		this.acaoRepositorio.findAll(exemplo).iterator().forEachRemaining(acoes::add);
		return acoes;
	}

}
