package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PremioMensal(
        @JsonProperty("max_amount")
        BigDecimal maxAmount,
        @JsonProperty("min_amount")
        BigDecimal minAmount,
        @JsonProperty("suggested_amount")
        BigDecimal suggestedAmount
) {}
