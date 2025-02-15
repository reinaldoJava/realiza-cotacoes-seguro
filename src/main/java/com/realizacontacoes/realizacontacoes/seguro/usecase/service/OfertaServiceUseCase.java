package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfertaServiceUseCase{

    private static final Logger LOGGER = LoggerFactory.getLogger(OfertaServiceUseCase.class);

    private final ConsultaOfertaServicePort consultaOfertaServicePort;

    public OfertaServiceUseCase(ConsultaOfertaServicePort consultaOfertaServicePort) {
        this.consultaOfertaServicePort = consultaOfertaServicePort;
    }
    public OfertaResponse getOferta(String ofertaId, String produtoId){

        OfertaResponse ofertaResponse = consultaOfertaServicePort.getOfertaById(ofertaId);
        LOGGER.info("Validacoes relacionadas a ofertas");
        ofertaResponse.validarAtividade();
        ofertaResponse.validarProdutoAssociado(produtoId);
        return ofertaResponse;
    }
}
