package br.com.cupomapi.cupomapi.controller;

import br.com.cupomapi.cupomapi.dto.CupomAggregationDTO;
import br.com.cupomapi.cupomapi.dto.CupomDTO;
import br.com.cupomapi.cupomapi.exception.RegraDeNegocioException;
import br.com.cupomapi.cupomapi.service.CupomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Listar cupons por email", description = "Listar cupons por email ")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de cubos por email "),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<CupomDTO> findByEmail(@RequestBody String email)throws RegraDeNegocioException {
        log.info("Buscando cupom por email...");
        CupomDTO cupom = cupomService.findByEmail(email);
        log.info("Cupom encontrado com sucesso.");
        return new ResponseEntity<>(cupom, HttpStatus.OK);
    }

    @Operation(summary = "Desativa cupom por email", description = "Desativa cupom por email")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Desativa cupom por email "),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/desativar-cupom")
    public ResponseEntity<Void> desativarCupom(@RequestBody String email) throws RegraDeNegocioException{
        log.info("Desativando cupom...");
        cupomService.desativarCupom(email);
        log.info("Cupom desativado com sucesso.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Gera lista de cupons por data de criação.", description = "Gera uma lista de logs por data de criação e quantidade")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Desativa cupom por email "),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/quantidade-Log")
    public ResponseEntity<List<CupomAggregationDTO>> groupByLog() {
        log.info("Gerando lista de cupons por data de criação...");
        List<CupomAggregationDTO> cupomAggregationDTO = cupomService.groupByLog();
        log.info("Lista de Cupons gerada com sucesso.");
        return new ResponseEntity<>(cupomAggregationDTO, HttpStatus.OK);
    }
}
