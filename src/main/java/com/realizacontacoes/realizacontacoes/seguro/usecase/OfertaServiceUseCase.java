package com.realizacontacoes.realizacontacoes.seguro.usecase;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.service.OfertaValidator;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OfertaServiceUseCase{

    private static final Logger LOGGER = LoggerFactory.getLogger(OfertaServiceUseCase.class);

    private final ConsultaOfertaServicePort consultaOfertaServicePort;

    public OfertaServiceUseCase(ConsultaOfertaServicePort consultaOfertaServicePort) {
        this.consultaOfertaServicePort = consultaOfertaServicePort;
    }
    public OfertaResponse getOferta(String ofertaId){

        OfertaResponse ofertaResponse = consultaOfertaServicePort.getOfertaById(ofertaId);
        LOGGER.info("Validacoes relacionadas a ofertas");
        return ofertaResponse;
    }
}
