package com.javaee.restswagger.services;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.javaee.restswagger.domain.Pessoa;
import com.javaee.restswagger.repository.PessoaRepositorio;

@Service
public class PessoaServicoImpl implements PessoaServico {

	private PessoaRepositorio pessoaRepositorio;
	
	public PessoaServicoImpl(PessoaRepositorio pessoaRepositorio) {
		this.pessoaRepositorio = pessoaRepositorio;
	}
	@Override
	public Set<Pessoa> getAll() {
		Set<Pessoa> pessoas = new HashSet<>();
		this.pessoaRepositorio.findAll().iterator().forEachRemaining(pessoas::add);
		return pessoas;
	}

	@Override
	public Pessoa getPessoaById(String id) {
		return getById(id);
	}
	
	private Pessoa getById(String id) {
		
		Optional<Pessoa> pessoaOptional = pessoaRepositorio.findById(id);
		if (!pessoaOptional.isPresent()) {
            throw new IllegalArgumentException("Não existe pessoa cadastrada com o id: " + id.toString() );
        }
		return pessoaOptional.get();
	}
	
	@Override
	public Pessoa criarNovaPessoa(Pessoa pessoa) {
		return pessoaRepositorio.save(pessoa);
	}

	@Override
	public Pessoa salvarPessoa(String id, Pessoa pessoa) {
		pessoa.setId(id);
		Pessoa pessoaInDb = this.pessoaRepositorio.save(pessoa);
		return pessoaInDb;
	}

	@Override
	public void deletePessoaById(String id) {
		pessoaRepositorio.deleteById(id);
		
	}
}
