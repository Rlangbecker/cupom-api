package br.com.cupomapi.cupomapi.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
public class CupomAggregationDTO {

    @Field("_id")
    private LocalDate dataCriacao;

    private Integer quantidade;
}
