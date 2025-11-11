package com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cotacao")
@Validated
public class CotacaoController {

    private final ProcessaCotacaoPort processaCotacaoPort;

    public CotacaoController(ProcessaCotacaoPort processaCotacaoPort) {
        this.processaCotacaoPort = processaCotacaoPort;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<String> getCotacao(@Valid @RequestBody InsuranceRequest request) {
        //Processa cotacao.
        processaCotacaoPort.processarCotacao(request);
        return ResponseEntity.ok("Cotação recebida com sucesso.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCotacaoPorId(@PathVariable Long id) {
       try {
            Cotacao cotacao = processaCotacaoPort.buscarCotacaoPorId(id);
            return ResponseEntity.ok(cotacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cotação não encontrada com o ID: " + id);
        }
    }
}
