package com.javaee.restswagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.javaee.restswagger.domain.Empresa;


@Repository
public interface EmpresaRepositorio extends MongoRepository<Empresa, String>{

}
