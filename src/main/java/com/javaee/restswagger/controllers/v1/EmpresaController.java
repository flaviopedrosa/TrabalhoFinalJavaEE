package com.javaee.restswagger.controllers.v1;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.restswagger.domain.Empresa;
import com.javaee.restswagger.services.EmpresaServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Api de Empresa.")
@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	 private final EmpresaServico empresaServico;

	    public EmpresaController(EmpresaServico empresaServico) {
	        this.empresaServico = empresaServico;
	    }

	    @ApiOperation(value = "Retorna a lista de empresas", notes="Esse endpoint retornará todas as empresas.")
	    @GetMapping
	    @ResponseStatus(HttpStatus.OK)
	    public Set<Empresa> getAll(){
	        return empresaServico.getAll();
	    }

	 
	    @ApiOperation(value = "Recupera a empresa pelo Id")
	    @GetMapping({"/{id}"})
	    @ResponseStatus(HttpStatus.OK)
	    public Empresa getById(@PathVariable String id){
	        return empresaServico.getEmpresaById(id);
	    }

	    @ApiOperation(value = "Cria um anova empresa")
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public Empresa create(@RequestBody Empresa empresa){
	        return empresaServico.criarNovaEmpresa(empresa);
	    }

	    @ApiOperation(value = "Atualiza uma empresa existente")
	    @PutMapping({"/{id}"})
	    @ResponseStatus(HttpStatus.OK)
	    public Empresa updateEmpresa(@PathVariable String id, @RequestBody Empresa empresa){
	        return empresaServico.salvarEmpresa(id, empresa);
	    }

	    @ApiOperation(value = "Exclui uma empresa.")
	    @DeleteMapping({"/{id}"})
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteEmpresa(@PathVariable String id){
	    	empresaServico.deleteEmpresaById(id);
	    }
	    
	    @ApiOperation(value = "Atualiza o valor de acao de uma empresa")
	    @PutMapping({"/{id}/Acao"})
	    @ResponseStatus(HttpStatus.OK)
	    public String atualizaValorDeAcao(@PathVariable String id, @RequestBody float percentual){
	        return "Novo Valor "  + empresaServico.ajustarValorDaAcao(percentual, id);
	    }
	    
	    @ApiOperation(value = "Dispoinibiliza mais ações de uma empresa")
	    @PutMapping({"/{id}/Acao/lanca"})
	    @ResponseStatus(HttpStatus.OK)
	    public String LancaAcoes(@PathVariable String id, @RequestBody int aumento){
	        return "Novo limite de ações disponíveis "  + empresaServico.aumentarNumeroDeAcoes(aumento, id);
	    }
}
