package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record OfertaResponse(String id,
                             String productId,
                             String name,
                             Instant createdAt,
                             boolean active,
                             Map<String, Double> coverages,
                             Set<String> assistances,
                             PremioMensal premioMensal
) {}

