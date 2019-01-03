package com.javaee.restswagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.javaee.restswagger.domain.Pessoa;

@Repository
public interface PessoaRepositorio extends MongoRepository<Pessoa, String>{

}
