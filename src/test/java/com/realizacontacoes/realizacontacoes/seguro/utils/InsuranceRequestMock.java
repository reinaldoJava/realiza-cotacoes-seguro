package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.CustomerRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.instancio.Instancio;
import org.instancio.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InsuranceRequestMock {

    public static InsuranceRequest criarInsuranceRequestValido() {
        return Instancio.of(InsuranceRequest.class)
                .set(Select.field("productId"), "1b2da7cc-b367-4196-8a8-9cfeec21f587")
                .set(Select.field("offerId"), "adc56d77-348c-4bf0-908f-22d402ee715c")
                .set(Select.field("category"), "HOME")
                .set(Select.field("totalMonthlyPremiumAmount"), BigDecimal.valueOf(75.25))
                .set(Select.field("totalCoverageAmount"), BigDecimal.valueOf(825000.00))
                .set(Select.field("coverages"), Map.of(
                        "Incêndio", BigDecimal.valueOf(250000.00),
                        "Desastres naturais", BigDecimal.valueOf(500000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(75000.00)
                ))
                .set(Select.field("assistances"), List.of("Encanador", "Eletricista", "Chaveiro 24h"))
                .set(Select.field("customer"), criarCustomerRequestValido())
                .create();
    }

    public static InsuranceRequest criarInsuranceRequestComAssistenciasInvalidas() {
        return Instancio.of(InsuranceRequest.class)
                .set(Select.field("assistances"), List.of("Assistencia Invalida"))
                .create();
    }

    public static InsuranceRequest criarInsuranceRequestComValorPremioMensalInvalido() {
        return Instancio.of(InsuranceRequest.class)
                .set(Select.field("totalMonthlyPremiumAmount"), BigDecimal.valueOf(1000.00))
                .create();
    }

    public static InsuranceRequest criarInsuranceRequestComTotalCoberturasInvalido() {
        return Instancio.of(InsuranceRequest.class)
                .set(Select.field("coverages"), Map.of(
                        "Incêndio", BigDecimal.valueOf(250000.00),
                        "Desastres naturais", BigDecimal.valueOf(500000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(75000.01)
                ))
                .create();
    }

    private static CustomerRequest criarCustomerRequestValido() {
        return Instancio.of(CustomerRequest.class)
                .set(Select.field("name"), "John Wick")
                .set(Select.field("type"), "NATURAL")
                .set(Select.field("gender"), "MALE")
                .set(Select.field("dateOfBirth"), "1973-05-02")
                .set(Select.field("email"), "johnwick@gmail.com")
                .set(Select.field("phoneNumber"), 11950503030L)
                .create();
    }
}