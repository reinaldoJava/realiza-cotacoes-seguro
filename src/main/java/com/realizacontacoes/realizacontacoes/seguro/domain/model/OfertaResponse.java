package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record OfertaResponse(String id,
                             String productId,
                             String name,
                             Instant createdAt,
                             boolean active,
                             Map<String, BigDecimal> coverages,
                             Set<String> assistencias,
                             PremioMensal premioMensal
) {public void validarAtividade() {
    if (!this.active) {
        throw new ValidationException("A oferta informada está inativa.");
    }
}

    public void validarProdutoAssociado(String produtoIdRequisitado) {
        if (!this.productId.equals(produtoIdRequisitado)) {
            throw new ValidationException("A oferta não pertence ao produto informado.");
        }
    }}

