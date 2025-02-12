package com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
