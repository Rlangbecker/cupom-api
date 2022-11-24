package br.com.cupomapi.cupomapi.repository;

import br.com.cupomapi.cupomapi.entity.CupomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CupomRepository extends MongoRepository<CupomEntity, Integer> {

}
