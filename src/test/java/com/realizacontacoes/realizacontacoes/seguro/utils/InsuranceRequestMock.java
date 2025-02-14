package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.CustomerRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InsuranceRequestMock {

    public static InsuranceRequest criarInsuranceRequestValido() {
        return new InsuranceRequest(
                "1b2da7cc-b367-4196-8a8-9cfeec21f587",
                "adc56d77-348c-4bf0-908f-22d402ee715c",
                "HOME",
                BigDecimal.valueOf(75.25),
                BigDecimal.valueOf(825000.00),
                Map.of(
                        "Incêndio", BigDecimal.valueOf(250000.00),
                        "Desastres naturais", BigDecimal.valueOf(500000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(75000.00)
                ),
                List.of("Encanador", "Eletricista", "Chaveiro 24h"),
                criarCustomerRequestValido()
        );
    }

    public static InsuranceRequest criarInsuranceRequestComAssistenciasInvalidas() {
        InsuranceRequest request = criarInsuranceRequestValido();
        return new InsuranceRequest(
                request.productId(),
                request.offerId(),
                request.category(),
                request.totalMonthlyPremiumAmount(),
                request.totalCoverageAmount(),
                request.coverages(),
                List.of("Assistencia Invalida"),
                request.customer()
        );
    }

    public static InsuranceRequest criarInsuranceRequestComValorPremioMensalInvalido() {
        InsuranceRequest request = criarInsuranceRequestValido();
        return new InsuranceRequest(
                request.productId(),
                request.offerId(),
                request.category(),
                BigDecimal.valueOf(1000.00),
                request.totalCoverageAmount(),
                request.coverages(),
                request.assistances(),
                request.customer()
        );
    }

    public static InsuranceRequest criarInsuranceRequestComTotalCoberturasInvalido() {
        InsuranceRequest request = criarInsuranceRequestValido();
        return new InsuranceRequest(
                request.productId(),
                request.offerId(),
                request.category(),
                request.totalMonthlyPremiumAmount(),
                request.totalCoverageAmount(),
                Map.of(
                        "Incêndio", BigDecimal.valueOf(250000.00),
                        "Desastres naturais", BigDecimal.valueOf(500000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(75000.01)
                ),
                request.assistances(),
                request.customer()
        );
    }

    private static CustomerRequest criarCustomerRequestValido() {
        return new CustomerRequest(
                "36205578900",
                "John Wick",
                "NATURAL",
                "MALE",
                "1973-05-02",
                "johnwick@gmail.com",
                11950503030L
        );
    }
}