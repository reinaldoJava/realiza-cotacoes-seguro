package com.realizacontacoes.realizacontacoes.seguro.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.PremioMensalDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record OfertaResponse(String id,
                             @JsonProperty("product_id")
                             String productId,
                             String name,
                             @JsonProperty("created_at") Instant createdAt,
                             boolean active,
                             Map<String, Double> coverages,
                             Set<String> assistencias,
                             PremioMensalDTO premioMensalDTO
) {
    public void validarAtividade() {
    if (!this.active) {
        throw new ValidationException("A oferta informada está inativa.");
    }
}

    public void validarProdutoAssociado(String produtoIdRequisitado) {
        if (!this.productId.equals(produtoIdRequisitado)) {
            throw new ValidationException("A oferta não pertence ao produto informado.");
        }
    }
}

