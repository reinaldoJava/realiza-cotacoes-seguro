package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record InsuranceRequest(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK)
        @JsonProperty("product_id")
        String productId,
        @JsonProperty("offer_id")
        String offerId,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK)
        String category,
        @Positive
        @JsonProperty("total_monthly_premium_amount")
        BigDecimal totalMonthlyPremiumAmount,
        @Positive
        @JsonProperty("total_coverage_amount")
        BigDecimal totalCoverageAmount,
        Map<String, BigDecimal> coverages,
        List<String> assistances,
        CustomerRequest customerRequest
) {
    public void validarAssistencias(List<String> assistenciasDisponiveis) {
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
