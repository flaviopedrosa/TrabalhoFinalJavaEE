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

import com.javaee.restswagger.domain.Acao;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.services.AcaoServico;
import com.javaee.restswagger.services.PessoaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Api de Pessoa.")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaServico pessoaServico;
    private final AcaoServico acaoServico;

    public PessoaController(PessoaServico pessoaServico, AcaoServico acaoServico) {
        this.pessoaServico = pessoaServico;
        this.acaoServico = acaoServico;
    }

    @ApiOperation(value = "Retorna a lista de pessoas", notes="Esse endpoint retornará todas as pessoas.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Pessoa> getAll(){
        return pessoaServico.getAll();
    }

 
    @ApiOperation(value = "Recupera a pessoa pelo Id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Pessoa getById(@PathVariable String id){
        return pessoaServico.getPessoaById(id);
    }

    @ApiOperation(value = "Cria um anova pessoa")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa create(@RequestBody Pessoa pessoa){
        return pessoaServico.criarNovaPessoa(pessoa);
    }

    @ApiOperation(value = "Atualiza uma pessoa existente")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Pessoa updatePessoa(@PathVariable String id, @RequestBody Pessoa pessoa){
        return pessoaServico.salvarPessoa(id, pessoa);
    }

    @ApiOperation(value = "Exclui uma pessoa.")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deletePessoa(@PathVariable String id){
    	pessoaServico.deletePessoaById(id);
    }
    
    @ApiOperation(value = "Recupera as ações de uma pessoa pelo Id")
    @GetMapping({"/{id}/acoes"})
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> Acoes(@PathVariable String id){
        return acaoServico.AcoesDeUmaPessoa(id);
    }
}
