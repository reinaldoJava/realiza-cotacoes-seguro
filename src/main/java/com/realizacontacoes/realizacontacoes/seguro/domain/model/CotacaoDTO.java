package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CotacaoDTO(
        Long id,
        String productId,
        String offerId,
        String category,
        BigDecimal totalMonthlyPremiumAmount,
        BigDecimal totalCoverageAmount,
        List<CoverageDTO> coverageDTOS,
        List<String> assistances,
        CustomerDTO customerDTO,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
