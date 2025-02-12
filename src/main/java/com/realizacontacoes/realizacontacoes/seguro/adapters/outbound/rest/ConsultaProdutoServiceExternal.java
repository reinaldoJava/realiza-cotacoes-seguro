package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.config.FeignClientConfig;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.ProdutoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//TODO Rever nome de tdas as classes.
@FeignClient(name = "externalService", url = "${feign.consulta.produto.url}", configuration = FeignClientConfig.class)
public interface ConsultaProdutoServiceExternal {
    @GetMapping("/consulta-produto/{id}")
    ProdutoResponse getProdutoById(@PathVariable("id")  String id);
}
