package com.javaee.restswagger.listeners;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import com.javaee.restswagger.config.RabbitMQConfig;
import com.javaee.restswagger.domain.Acao;
import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.repository.AcaoRepositorio;
import com.javaee.restswagger.services.EnvioEmailImpl;

@Component
public class TransacaoListener {
	final AcaoRepositorio acaoRepositorio;
	public TransacaoListener(AcaoRepositorio acaoRepositorio) {
		this.acaoRepositorio = acaoRepositorio;
	}
	 @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
	 public void processMessage(Acao acao) {
		 
	      EnvioEmailImpl envioEmail = new EnvioEmailImpl();
	      
	      Empresa empresaFiltro = new Empresa();
	      empresaFiltro.setId(acao.getEmpresa().getId());
	      
	      Pessoa compradorFiltro = new Pessoa();
	      compradorFiltro.setId(acao.getComprador().getId());
	      
	      Pessoa vendedorFiltro = new Pessoa();
	      vendedorFiltro.setId(acao.getVendedor().getId());
	      
	      Acao acaoExemplo = new Acao();
	      acaoExemplo.setEmpresa(empresaFiltro);
		  
	      Example<Acao> exemplo = Example.of(acaoExemplo);
	      
		  long qtdAcoesVendidas = acaoRepositorio.count(exemplo);
	      // se a empresa for quem esta vendendo a ação
	      if(acao.getEmpresa().getId().equals(acao.getVendedor().getId())) {
		      if(qtdAcoesVendidas > acao.getEmpresa().getNumeroAcoesMaximoParaVenda())
		    	  envioEmail.enviarAvisoDeFinalizacaoDeCompraComErro(acao.getComprador(), "Empresa já emitiu numero máximo de ações", acao.getVendedor());
		      else {
		    	  envioEmail.enviarAvisoDeFinalizacaoDeCompraComSucesso(acao.getEmpresa(), acao.getComprador(), acao.getVendedor(), acao);
		    	  acaoRepositorio.save(acao);
		      }
		  }
	      else {
	    	  Acao acaoExemplo2 = new Acao();
		      acaoExemplo2.setEmpresa(empresaFiltro);
		      acaoExemplo2.setComprador(vendedorFiltro);
			  Example<Acao> exemplo2 = Example.of(acaoExemplo2);
			  
	    	  Optional<Acao> acaoInDb = this.acaoRepositorio.findOne(exemplo2);
	    	  if(!acaoInDb.isPresent()) {
	    		  String motivo = "O vendedor " + acao.getVendedor().getNome() + " não possui ações da empresa " + acao.getEmpresa().getNome() + " para vender.";
	    		  envioEmail.enviarAvisoDeFinalizacaoDeCompraComErro(acao.getComprador(), motivo, acao.getVendedor());
	    	  }else{
	    		  acaoInDb.get().setComprador(acao.getComprador());
	    		  acaoInDb.get().setVendedor(acao.getVendedor());
		    	  acaoRepositorio.save(acaoInDb.get());
		    	  envioEmail.enviarAvisoDeFinalizacaoDeCompraComSucesso(acao.getEmpresa(), acao.getComprador(), acao.getVendedor(), acao);
	    	  }
	      }
	 }     
}
