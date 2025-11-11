package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.math.BigDecimal;

public record Coverage(Long id,
                       String type,
                       BigDecimal amount,
                       Long cotacaoId) {
}
