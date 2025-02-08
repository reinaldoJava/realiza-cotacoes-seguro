package com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.CotacaoServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cotacao")
@Validated
public class CotacaoController {

    private final CotacaoServicePort cotacaoServicePort;

    public CotacaoController(CotacaoServicePort cotacaoServicePort) {
        this.cotacaoServicePort = cotacaoServicePort;
    }

    @GetMapping
    public ResponseEntity<String> getCotacao(@Valid @RequestBody InsuranceRequest request) {
        cotacaoServicePort.getExternalData(request.productId());
        return ResponseEntity.ok("Cotação recebida com sucesso.");
    }
}
