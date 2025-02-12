package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.PremioMensal;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.OfertaResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class OfertaResponseMock {

    public static OfertaResponse criarOfertaResponseAtiva() {
        return new OfertaResponse(
                "adc56d77-348c-4bf0-908f-22d402ee715c",
                "1b2da7cc-b367-4196-8a78-9cfeec21f587",
                "Seguro de Vida Familiar",
                Instant.parse("2021-07-01T00:00:00Z"),
                true,
                Map.of(
                        "Incêndio", BigDecimal.valueOf(500000.00),
                        "Desastres naturais", BigDecimal.valueOf(600000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(80000.00),
                        "Roubo", BigDecimal.valueOf(100000.00)
                ),
                Set.of("Encanador", "Eletricista", "Chaveiro 24h", "Assistência Funerária"),
                new PremioMensal(BigDecimal.valueOf(100.74), BigDecimal.valueOf(50.00), BigDecimal.valueOf(60.25))
        );
    }
    public static OfertaResponse criarOfertaResponseInativa() {
        return new OfertaResponse(
                "adc56d77-348c-4bf0-908f-22d402ee715c",
                "1b2da7cc-b367-4196-8a78-9cfeec21f587",
                "Seguro de Vida Familiar",
                Instant.parse("2021-07-01T00:00:00Z"),
                false,
                Map.of(
                        "Incêndio", BigDecimal.valueOf(500000.00),
                        "Desastres naturais", BigDecimal.valueOf(600000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(80000.00),
                        "Roubo", BigDecimal.valueOf(100000.00)
                ),
                Set.of("Encanador", "Eletricista", "Chaveiro 24h", "Assistência Funerária"),
                new PremioMensal(BigDecimal.valueOf(100.74), BigDecimal.valueOf(50.00), BigDecimal.valueOf(60.25))
        );
    }
    public static OfertaResponse criarOfertaResponseComProdutoDiferente() {
        return new OfertaResponse(
                "adc56d77-348c-4bf0-908f-22d402ee715c",
                "1b2da7cc-b367-4196-8a78-9cfeec-OUTRO",
                "Seguro de Vida Familiar",
                Instant.parse("2021-07-01T00:00:00Z"),
                true,
                Map.of(
                        "Incêndio", BigDecimal.valueOf(500000.00),
                        "Desastres naturais", BigDecimal.valueOf(600000.00),
                        "Responsabilidade civil", BigDecimal.valueOf(80000.00),
                        "Roubo", BigDecimal.valueOf(100000.00)
                ),
                Set.of("Encanador", "Eletricista", "Chaveiro 24h", "Assistência Funerária"),
                new PremioMensal(BigDecimal.valueOf(100.74), BigDecimal.valueOf(50.00), BigDecimal.valueOf(60.25))
        );
    }
}
