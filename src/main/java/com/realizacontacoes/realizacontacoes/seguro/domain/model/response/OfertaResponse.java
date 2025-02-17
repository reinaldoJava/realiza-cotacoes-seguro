package com.realizacontacoes.realizacontacoes.seguro.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.PremioMensal;

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
                             PremioMensal premioMensal
) {
}

