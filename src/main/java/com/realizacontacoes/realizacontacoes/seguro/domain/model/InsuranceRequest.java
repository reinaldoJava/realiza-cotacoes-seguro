package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record InsuranceRequest(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String productId,
        String offerId,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String category,
        @Positive BigDecimal totalMonthlyPremiumAmount,
        @Positive BigDecimal totalCoverageAmount,
        Map<String, BigDecimal> coverages,
        List<String> assistances,
        Customer customer
) {}
