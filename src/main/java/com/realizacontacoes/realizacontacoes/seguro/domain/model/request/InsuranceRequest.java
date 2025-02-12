package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record InsuranceRequest(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String productId,
        String offerId,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String category,
        @Positive BigDecimal totalMonthlyPremiumAmount,
        @Positive BigDecimal totalCoverageAmount,
        Map<String, BigDecimal> coverages,
        List<String> assistances,
        CustomerRequest customerRequest
) {
    public void validarAssistencias(Set<String> assistenciasDisponiveis) {
    if (!assistenciasDisponiveis.containsAll(this.assistances)) {
        throw new ValidationException("Uma ou mais assistências não estão disponíveis na oferta.");
    }
}

    public void validarValorPremioMensal(BigDecimal min, BigDecimal max) {
        if (this.totalMonthlyPremiumAmount.compareTo(min) < 0 ||
                this.totalMonthlyPremiumAmount.compareTo(max) > 0) {
            throw new ValidationException("O valor do prêmio mensal está fora do intervalo permitido.");
        }
    }

    public void validarTotalCoberturas(BigDecimal totalCoberturaEsperado) {
        BigDecimal somaCoberturas = this.coverages.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (somaCoberturas.compareTo(totalCoberturaEsperado) != 0) {
            throw new ValidationException("O valor total das coberturas não corresponde ao esperado para a oferta.");
        }
    }}
