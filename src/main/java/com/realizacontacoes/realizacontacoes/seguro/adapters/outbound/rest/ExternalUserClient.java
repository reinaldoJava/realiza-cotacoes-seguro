package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.config.FeignClientConfig;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//TODO Rever nome de tdas as classes.
@FeignClient(name = "externalService", url = "http://external-service.com", configuration = FeignClientConfig.class)
public interface ExternalUserClient {
    @GetMapping("/external/data")
    ProductResponse getExternalData(@RequestParam("id") String id);
}
