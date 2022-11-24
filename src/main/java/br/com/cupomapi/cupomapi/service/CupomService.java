package br.com.cupomapi.cupomapi.service;


import br.com.cupomapi.cupomapi.dto.CupomDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class CupomService {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            clientIdPrefix = "{$spring.kafka.consumer.group-id}",
            groupId = "{$spring.kafka.consumer.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"0"})}
    )
    public void consumirCupom(@Payload String mensagem) throws JsonProcessingException {
        CupomDTO cupomDTO = objectMapper.readValue(mensagem, CupomDTO.class);
        log.info("{}: {} {} {}", cupomDTO.getEmail(),cupomDTO.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                cupomDTO.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),cupomDTO.getDesconto());

    }
}