package br.com.cupomapi.cupomapi.service;


import br.com.cupomapi.cupomapi.dto.CupomAggregationDTO;
import br.com.cupomapi.cupomapi.dto.CupomDTO;
import br.com.cupomapi.cupomapi.entity.CupomEntity;
import br.com.cupomapi.cupomapi.exception.RegraDeNegocioException;
import br.com.cupomapi.cupomapi.repository.CupomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CupomService {
    private final ObjectMapper objectMapper;
    private final CupomRepository cupomRepository;

    @KafkaListener(
            clientIdPrefix = "{$spring.kafka.consumer.group-id}",
            groupId = "{$spring.kafka.consumer.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"1"})}
    )
    public void consumirCupom(@Payload String mensagem) throws JsonProcessingException {
        CupomDTO cupomDTO = objectMapper.readValue(mensagem, CupomDTO.class);
        CupomEntity cupom = objectMapper.convertValue(cupomDTO, CupomEntity.class);
        cupomRepository.save(cupom);

        log.info("{}: {} {} {}", cupomDTO.getEmail(),cupomDTO.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                cupomDTO.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),cupomDTO.getDesconto());
    }

    public CupomDTO findByEmail(String email){
        CupomEntity cupom = cupomRepository.findFirstByEmailAndAtivo(email, true);
        return objectMapper.convertValue(cupom, CupomDTO.class);
    }

    public void desativarCupom(String email){
        CupomEntity cupom = cupomRepository.findFirstByEmailAndAtivo(email, true);
        cupom.setAtivo(false);
        cupomRepository.save(cupom);
    }

    @Scheduled(cron = "0 1 * * * *")
    public void desativarCupomVencido(){
        List<CupomEntity> cupons = cupomRepository.findAllByDataVencimento(LocalDate.now().minusDays(1))
                .stream()
                .map(cupom -> {
                    cupom.setAtivo(false);
                    return cupom;
                }).toList();
        cupomRepository.saveAll(cupons);
    }

    public List<CupomAggregationDTO> groupByLog() {
        return CupomRepository.groupByTipo();
    }
}