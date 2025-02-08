package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.config.FeignClientConfig;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "externalService", url = "http://external-service.com", configuration = FeignClientConfig.class)
public interface ExternalUserClient {
    @GetMapping("/external/data")
    InsurancePolicy getExternalData(@RequestParam("id") String id);
}
