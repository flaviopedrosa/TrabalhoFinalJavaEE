package com.javaee.restswagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.javaee.restswagger.domain.PreTransacao;



@Repository
public interface PreTransacaoRepositorio  extends MongoRepository<PreTransacao, String>{

}
