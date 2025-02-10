package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;

public class ConsultaOfertaAdapter implements ConsultaOfertaServicePort {

    private final ConsultaOfertaServiceExternal consultaOfertaServiceExternal;

    public ConsultaOfertaAdapter(ConsultaOfertaServiceExternal consultaOfertaServiceExternal) {
        this.consultaOfertaServiceExternal = consultaOfertaServiceExternal;
    }

    @Override
    public OfertaResponse getOfertaById(String id) {

        return consultaOfertaServiceExternal.getOfertaById(id);
    }
}
