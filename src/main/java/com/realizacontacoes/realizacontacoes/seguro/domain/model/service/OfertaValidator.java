package com.realizacontacoes.realizacontacoes.seguro.domain.model.service;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.*;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OfertaValidator {

    public void validarOfertaAtiva(OfertaResponse oferta) {
        if (!oferta.active()) {
            throw new OfertaInativaException("A oferta não está ativa");
        }
    }

    public void validarCoberturas(OfertaResponse oferta, Map<String, BigDecimal> coberturas) {
        if (!oferta.coverages().keySet().containsAll(coberturas.keySet())) {
            throw new CoberturaInvalidaException("Existem coberturas informadas que não pertencem à oferta.");
        }

        for (Map.Entry<String, BigDecimal> entry : coberturas.entrySet()) {
            BigDecimal valorInformado = entry.getValue();
            BigDecimal valorMaximoPermitido = BigDecimal.valueOf(oferta.coverages().get(entry.getKey()));

            if (valorMaximoPermitido == null || valorInformado.compareTo(valorMaximoPermitido) > 0) {
                throw new CoberturaInvalidaException("Cobertura " + entry.getKey() + " excede o valor máximo permitido.");
            }
        }
    }

    public void validarAssistencias(OfertaResponse oferta, List<String> assistencias) {
        if (assistencias.stream()
                .anyMatch(assistencia -> !oferta.assistencias().contains(assistencia))) {
            throw new AssistenciaInvalidaException("As assistências são inválidas");
        }
    }

    public void validarPremioMensal(OfertaResponse oferta, BigDecimal premioMensal) {
        if (premioMensal.compareTo(oferta.premioMensal().minAmount()) < 0
                || premioMensal.compareTo(oferta.premioMensal().maxAmount()) > 0) {
            throw new PremioMensalInvalidoException("O prêmio mensal é inválido");
        }
    }

    public void validarValorTotalCoberturas(Map<String, BigDecimal> coberturas, BigDecimal valorTotalCoberturas) {
        if (coberturas.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .compareTo(valorTotalCoberturas) != 0) {
            throw new ValorTotalCoberturasInvalidoException("O valor total das coberturas é inválido");
        }
    }
}
