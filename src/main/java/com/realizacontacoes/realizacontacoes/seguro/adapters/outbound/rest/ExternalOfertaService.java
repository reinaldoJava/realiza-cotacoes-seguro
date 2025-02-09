package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.config.FeignClientConfig;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "externalOfferService", url = "http://external-service.com", configuration = FeignClientConfig.class)
public interface ExternalOfertaService {
    @GetMapping("/external/offer/{id}")
    OfertaResponse getOfferById(@PathVariable("id") String offerId);
}
