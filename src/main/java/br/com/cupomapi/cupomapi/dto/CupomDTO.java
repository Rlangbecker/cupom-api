package br.com.cupomapi.cupomapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CupomDTO {

    private String email;

    private Double desconto;

    private LocalDate dataCriacao;

    private LocalDate dataVencimento;
}