package com.realizacontacoes.realizacontacoes.seguro.domain.model.service;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.*;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.utils.OfertaResponseMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfertaValidatorTest {
    @InjectMocks
    private OfertaValidator ofertaValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve validar oferta ativa")
    void validarOfertaAtiva() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        assertDoesNotThrow(() -> ofertaValidator.validarOfertaAtiva(ofertaResponse));
    }

    @Test
    @DisplayName("Deve lançar exceção para oferta inativa")
    void validarOfertaInativa() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseInativa();
        assertThrows(OfertaInativaException.class, () -> ofertaValidator.validarOfertaAtiva(ofertaResponse));
    }

    @Test
    @DisplayName("Deve validar coberturas válidas")
    void validarCoberturasValidas() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        Map<String, BigDecimal> coberturas = Map.of("Incêndio", BigDecimal.valueOf(500000.00));
        assertDoesNotThrow(() -> ofertaValidator.validarCoberturas(ofertaResponse, coberturas));
    }

    @Test
    @DisplayName("Deve lançar exceção para coberturas inválidas")
    void validarCoberturasInvalidas() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        Map<String, BigDecimal> coberturas = Map.of("Incêndio", BigDecimal.valueOf(600000.00));
        assertThrows(CoberturaInvalidaException.class, () -> ofertaValidator.validarCoberturas(ofertaResponse, coberturas));
    }

    @Test
    @DisplayName("Deve validar assistências válidas")
    void validarAssistenciaValida() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        List<String> assistencias = List.of("Encanador");
        assertDoesNotThrow(() -> ofertaValidator.validarAssistencias(ofertaResponse, assistencias));
    }

    @Test
    @DisplayName("Deve lançar exceção para assistências inválidas")
    void validarAssistenciaInvalida() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        List<String> assistencias = List.of("Assistência inválida");
        assertThrows(AssistenciaInvalidaException.class, () -> ofertaValidator.validarAssistencias(ofertaResponse, assistencias));
    }

    @Test
    @DisplayName("Deve validar prêmio mensal válido")
    void validarPremioMensalValido() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        BigDecimal premioMensal = BigDecimal.valueOf(60.25);
        assertDoesNotThrow(() -> ofertaValidator.validarPremioMensal(ofertaResponse, premioMensal));
    }

    @Test
    @DisplayName("Deve lançar exceção para prêmio mensal inválido")
    void validarPremioMensalInvalido() {
        OfertaResponse ofertaResponse = OfertaResponseMock.criarOfertaResponseAtiva();
        BigDecimal premioMensal = BigDecimal.valueOf(150.00);
        assertThrows(PremioMensalInvalidoException.class, () -> ofertaValidator.validarPremioMensal(ofertaResponse, premioMensal));
    }

    @Test
    @DisplayName("Deve validar valor total de coberturas válido")
    void validarValorTotalCoberturasValido() {
        Map<String, BigDecimal> coberturas = Map.of("Incêndio", BigDecimal.valueOf(250000.00), "Desastres naturais", BigDecimal.valueOf(500000.00), "Responsabilidade civil", BigDecimal.valueOf(75000.00));
        BigDecimal valorTotalCoberturas = BigDecimal.valueOf(825000.00);
        assertDoesNotThrow(() -> ofertaValidator.validarValorTotalCoberturas(coberturas, valorTotalCoberturas));
    }

    @Test
    @DisplayName("Deve lançar exceção para valor total de coberturas inválido")
    void validarValorTotalCoberturasInvalido() {
        Map<String, BigDecimal> coberturas = Map.of("Incêndio", BigDecimal.valueOf(250000.00), "Desastres naturais", BigDecimal.valueOf(500000.00), "Responsabilidade civil", BigDecimal.valueOf(75000.00));
        BigDecimal valorTotalCoberturas = BigDecimal.valueOf(850000.00);
        assertThrows(ValorTotalCoberturasInvalidoException.class, () -> ofertaValidator.validarValorTotalCoberturas(coberturas, valorTotalCoberturas));
    }
}
