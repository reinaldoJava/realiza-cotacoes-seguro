package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.PremioMensal;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import org.instancio.Instancio;
import org.instancio.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OfertaResponseMock {

    public static OfertaResponse criarOfertaResponseComProdutoDiferente() {
        return Instancio.of(OfertaResponse.class)
                .set(Select.field("productId"), "1b2da7cc-b367-4196-8a78-9cfeec-OUTRO")
                .create();
    }

    public static OfertaResponse criarOfertaResponseInativa() {
        return Instancio.of(OfertaResponse.class)
                .set(Select.field("active"), false)
                .create();
    }

    public static OfertaResponse criarOfertaResponseAtiva() {
        return Instancio.of(OfertaResponse.class)
                .set(Select.field("active"), true)
                .create();
    }
    public static OfertaResponse criarOfertaResponseCoberturasValidas() {
        return Instancio.of(OfertaResponse.class)
                .set(Select.field("active"), true)
                .set(Select.field("coverages"),Map.of("Cobertura 1", BigDecimal.valueOf(100)))
                .create();
    }
    public static OfertaResponse criarOfertaResponseValidarAssistenciaValida() {
        return Instancio.of(OfertaResponse.class)
                .set(Select.field("assistencias"), List.of("Assistência 1"))
                .create();
    }
    public static OfertaResponse criarOfertaResponseValidarPremioMensalValido() {
        return Instancio.of(OfertaResponse.class)
                .set(Select.field("premioMensal"), new PremioMensal(BigDecimal.valueOf(100),
                        BigDecimal.valueOf(50),BigDecimal.ZERO ))
                .create();
    }
}
