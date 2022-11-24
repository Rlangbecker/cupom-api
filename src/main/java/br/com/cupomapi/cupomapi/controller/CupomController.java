package br.com.cupomapi.cupomapi.controller;

import br.com.cupomapi.cupomapi.dto.CupomDTO;
import br.com.cupomapi.cupomapi.exception.RegraDeNegocioException;
import br.com.cupomapi.cupomapi.service.CupomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cupom")
public class CupomController {

    private final CupomService cupomService;

    @GetMapping
    public ResponseEntity<CupomDTO> findByEmail(@RequestParam String email)throws RegraDeNegocioException {
        log.info("Buscando cupom por email...");
        CupomDTO cupom = cupomService.findByEmail(email);
        log.info("Cupom encontrado com sucesso.");
        return new ResponseEntity<>(cupom, HttpStatus.OK);
    }
}
