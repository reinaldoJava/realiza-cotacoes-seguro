package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record Cotacao(
        Long id,
        String productId,
        String insurancePolicyId,
        String offerId,
        String category,
        BigDecimal totalMonthlyPremiumAmount,
        BigDecimal totalCoverageAmount,
        String createdAt,
        String updatedAt,
        List<String> assistances,
        Map<String, Double> coverages,
        Customer customer
) {
}