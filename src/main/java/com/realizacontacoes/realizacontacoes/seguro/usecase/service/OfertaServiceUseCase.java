package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.OfertaServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;

public class OfertaServiceUseCase implements OfertaServicePort {

    private final ConsultaOfertaServicePort consultaOfertaServicePort;

    public OfertaServiceUseCase(ConsultaOfertaServicePort consultaOfertaServicePort) {
        this.consultaOfertaServicePort = consultaOfertaServicePort;
    }
    public OfertaResponse getOferta(String id){
        return consultaOfertaServicePort.getOfertaById(id);
    }
}
