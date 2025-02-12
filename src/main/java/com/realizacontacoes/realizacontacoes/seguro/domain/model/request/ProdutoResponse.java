package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record ProdutoResponse(
        String id,
        String name,
        @JsonProperty("created_at")
        Instant createdAt,
        Boolean active,
        @JsonProperty("offers")
        List<String> ofertas
) {}
