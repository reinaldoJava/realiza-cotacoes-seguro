package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;

public record MonthlyPremiumAmount(
        BigDecimal max_amount,
        BigDecimal min_amount,
        BigDecimal suggested_amount
) {}
