package com.javaee.restswagger.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.javaee.restswagger.services.AcaoServico;

import io.swagger.annotations.Api;

@Api("Api de Transações.")
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	private final AcaoServico transacaoServico;
	
	public TransacaoController(AcaoServico transacaoServico){
		
		this.transacaoServico = transacaoServico;
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String IniciarTransacao(@RequestBody String idComprador, String idVendedor, String idEmpresa){
		String msg = transacaoServico.comprarAcao(idComprador, idVendedor, idEmpresa);
        return msg;
    }
}
