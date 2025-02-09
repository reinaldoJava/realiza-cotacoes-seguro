package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.OfertaServicePort;

public class OfertaServiceUseCase {

    private final OfertaServicePort ofertaServicePort;

    public OfertaServiceUseCase(OfertaServicePort ofertaServicePort) {
        this.ofertaServicePort = ofertaServicePort;
    }
    public OfertaResponse getOferta(String id){
        return ofertaServicePort.getOfertaById(id);
    }
}
