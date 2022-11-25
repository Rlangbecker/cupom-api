package br.com.cupomapi.cupomapi.repository;

import br.com.cupomapi.cupomapi.dto.CupomAggregationDTO;
import br.com.cupomapi.cupomapi.entity.CupomEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CupomRepository extends MongoRepository<CupomEntity, Integer> {

   CupomEntity findFirstByEmailAndAtivo(String email, boolean ativo);

   List<CupomEntity> findAllByDataVencimento(LocalDate data);

   @Aggregation(pipeline = {
           "{'$group':{'_id': '$dataCriacao', 'quantidade' : {'$sum': 1} }}"
   })
   List<CupomAggregationDTO> groupByTipo();
}
