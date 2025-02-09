package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;

public record PremioMensal(
        BigDecimal max_amount,
        BigDecimal min_amount,
        BigDecimal suggested_amount
) {}
