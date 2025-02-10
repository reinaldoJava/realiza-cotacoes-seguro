package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.List;

public record InsurancePolicy(
        String id,
        String product_id,
        String name,
        ZonedDateTime created_at,
        Boolean active,
        Map<String, BigDecimal> coverages,
        List<String> assistances,
        PremioMensal monthly_premium_amount
){}
