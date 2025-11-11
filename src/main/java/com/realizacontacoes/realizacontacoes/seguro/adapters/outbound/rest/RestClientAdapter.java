package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class RestClientAdapter implements ConsultaOfertaServicePort {

    private final WebClient webClient;
    @Value("${feign.consulta.oferta.url}")
    private String url;

    public RestClientAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public OfertaResponse getOfertaById(String id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.build(url+"/consulta-oferta/{id}"))
                .header("","")
                .retrieve()
                .bodyToMono(OfertaResponse.class)
                .block();

    }
}
