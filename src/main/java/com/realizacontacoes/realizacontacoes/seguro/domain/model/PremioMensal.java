package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;

public record PremioMensal(
        BigDecimal maxAmount,
        BigDecimal minAmount,
        BigDecimal suggestedAmount
) {}
