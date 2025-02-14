package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;

public record PremioMensalDTO(
        BigDecimal maxAmount,
        BigDecimal minAmount,
        BigDecimal suggestedAmount
) {}
