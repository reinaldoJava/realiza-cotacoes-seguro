package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.config.FeignClientConfig;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consultarOferta", url = "${feign.consulta.oferta.url}", configuration = FeignClientConfig.class)
public interface ConsultaOfertaServiceExternal {
    @GetMapping("/consulta-oferta/{id}")
    OfertaResponse getOfertaById(@PathVariable("id") String offerId);
}

