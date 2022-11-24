package br.com.cupomapi.cupomapi.repository;

import br.com.cupomapi.cupomapi.entity.CupomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CupomRepository extends MongoRepository<CupomEntity, Integer> {

   CupomEntity findByEmail(String email);

}
