package br.com.cupomapi.cupomapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cupom")
public class CupomEntity {

    @Id
    private String id;

    private String email;

    private boolean ativo;

    private Double desconto;

    private LocalDate dataCriacao;

    private LocalDate dataVencimento;
}
