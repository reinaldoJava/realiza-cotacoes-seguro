package com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.EnviaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.OfertaServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProdutoServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.SalvarContacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.RecebeCotacaoPort;
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

    private final ProdutoServicePort produtoServicePort;
    private final OfertaServicePort ofertaServicePort;
    private final EnviaCotacaoPort enviaCotacaoPort;
    private final SalvarContacaoPort salvarContacaoPort;


    public CotacaoController(ProdutoServicePort produtoServicePort, OfertaServicePort ofertaServicePort, EnviaCotacaoPort enviaCotacaoPort, RecebeCotacaoPort recebeCotacaoPort, SalvarContacaoPort salvarContacaoPort) {
        this.produtoServicePort = produtoServicePort;
        this.ofertaServicePort = ofertaServicePort;
        this.enviaCotacaoPort = enviaCotacaoPort;
        this.salvarContacaoPort = salvarContacaoPort;
    }

    @GetMapping
    public ResponseEntity<String> getCotacao(@Valid @RequestBody InsuranceRequest request) {
        //Busca informações do produto.
        ProdutoResponse response = produtoServicePort.getProduto(request.productId());
        //Busca informações da oferta.
        OfertaResponse ofertaResponse = ofertaServicePort.getOferta(response.id());
        //Salva no banco.
        salvarContacaoPort.processarCotacao(request);
        //Envia ao Kafka.
        enviaCotacaoPort.enviarCotacao(request);


        return ResponseEntity.ok("Cotação recebida com sucesso.");
    }
}
