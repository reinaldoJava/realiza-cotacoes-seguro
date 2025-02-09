package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.time.Instant;
import java.util.List;

public record ProductResponse(
        String id,
        String name,
        Instant createdAt,
        boolean active,
        List<String> offers
) {}
