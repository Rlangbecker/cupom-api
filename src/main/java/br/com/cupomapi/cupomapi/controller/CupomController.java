package br.com.cupomapi.cupomapi.controller;

import br.com.cupomapi.cupomapi.dto.CupomAggregationDTO;
import br.com.cupomapi.cupomapi.dto.CupomDTO;
import br.com.cupomapi.cupomapi.exception.RegraDeNegocioException;
import br.com.cupomapi.cupomapi.service.CupomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cupom")
public class CupomController {

    private final CupomService cupomService;

    @GetMapping
    public ResponseEntity<CupomDTO> findByEmail(@RequestBody String email)throws RegraDeNegocioException {
        log.info("Buscando cupom por email...");
        CupomDTO cupom = cupomService.findByEmail(email);
        log.info("Cupom encontrado com sucesso.");
        return new ResponseEntity<>(cupom, HttpStatus.OK);
    }

    @GetMapping("/desativar-cupom")
    public ResponseEntity<Void> desativarCupom(@RequestBody String email) throws RegraDeNegocioException{
        log.info("Desativando cupom...");
        cupomService.desativarCupom(email);
        log.info("Cupom desativado com sucesso.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/quantidade-Log")
    public ResponseEntity<List<CupomAggregationDTO>> groupByLog() {
        log.info("Gerando lista de cupons por data de criação...");
        List<CupomAggregationDTO> cupomAggregationDTO = cupomService.groupByLog();
        log.info("Lista de Cupons gerada com sucesso.");
        return new ResponseEntity<>(cupomAggregationDTO, HttpStatus.OK);
    }
}
