package br.com.cupomapi.cupomapi.repository;

import br.com.cupomapi.cupomapi.entity.CupomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CupomRepository extends MongoRepository<CupomEntity, Integer> {

   CupomEntity findFirstByEmailAndAtivo(String email, boolean ativo);

   List<CupomEntity> findAllByDataVencimento(LocalDate data);

}
