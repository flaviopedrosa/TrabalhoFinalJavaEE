package com.javaee.restswagger.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.javaee.restswagger.domain.Acao;


@Repository
public interface AcaoRepositorio extends MongoRepository<Acao, String>{
	/*@Query("SELECT COUNT(u) FROM Acao u WHERE u.empresa.id=?1")
    int qtdAcoesVendidas(String idEmpresa);
	
	@Query("SELECT u FROM Acao u WHERE u.empresa.id=?1 && u.comprador.id=?2 order by u.dataCompra limit 1" )
     Acao getAcao(String idEmpresa, String idComprador);
	
	@Query("SELECT u FROM Acao u WHERE u.empresa.id=?1 && u.comprador.id=?2 order by u.dataCompra" )
    Set<Acao> getAcoes(String idComprador, String idEmpresa);
	
	@Query("SELECT u FROM Acao u WHERE u.comprador.id=?2 order by u.dataCompra" )
    Set<Acao> getAcoes(String idComprador); */
}
