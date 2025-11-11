package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;
import com.realizacontacoes.realizacontacoes.seguro.utils.InsuranceRequestMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsuranceRequestTest {

    private final List<String> assistencias = List.of("Encanador", "Eletricista", "Chaveiro 24h");
    private final BigDecimal min = BigDecimal.valueOf(50.00);
    private final BigDecimal max = BigDecimal.valueOf(100.00);
    private final BigDecimal totalCoberturaEsperado = BigDecimal.valueOf(825000.00);
    @Test
    @DisplayName("Validar assistências")
    public void testValidarAssistenciasAssistenciasValidasNaoDeveLancarExcecao() {
        InsuranceRequest request = InsuranceRequestMock.criarInsuranceRequestValido();
        request.validarAssistencias(assistencias);
    }

    @Test
    @DisplayName("Validar assistências inválidas")
    public void testValidarAssistenciasAssistenciasInvalidasDeveLancarExcecao() {
        InsuranceRequest request = InsuranceRequestMock.criarInsuranceRequestComAssistenciasInvalidas();
        assertThrows(ValidationException.class, () -> request.validarAssistencias(assistencias));
    }

    @Test
    @DisplayName("Validar valor do prêmio mensal")
    public void testValidarValorPremioMensalValorValidoNaoDeveLancarExcecao() {
        InsuranceRequest request = InsuranceRequestMock.criarInsuranceRequestValido();
        request.validarValorPremioMensal(min, max);
    }

    @Test
    @DisplayName("Validar valor do prêmio mensal inválido")
    public void testValidarValorPremioMensalValorInvalidoDeveLancarExcecao() {
        InsuranceRequest request = InsuranceRequestMock.criarInsuranceRequestComValorPremioMensalInvalido();
        assertThrows(ValidationException.class, () -> request.validarValorPremioMensal(min, max));
    }

    @Test
    @DisplayName("Validar total de coberturas")
    public void testValidarTotalCoberturasTotalValidoNaoDeveLancarExcecao() {
        InsuranceRequest request = InsuranceRequestMock.criarInsuranceRequestValido();
        request.validarTotalCoberturas(totalCoberturaEsperado);
    }

    @Test
    @DisplayName("Validar total de coberturas inválido")
    public void testValidarTotalCoberturasTotalInvalidoDeveLancarExcecao() {
        
        InsuranceRequest request = InsuranceRequestMock.criarInsuranceRequestComTotalCoberturasInvalido();
        assertThrows(ValidationException.class, () -> request.validarTotalCoberturas(totalCoberturaEsperado));
    }
}