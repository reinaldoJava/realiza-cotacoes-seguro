package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsultaOfertaAdapter implements ConsultaOfertaServicePort {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaOfertaServicePort.class);

    private final ConsultaOfertaServiceExternal consultaOfertaServiceExternal;

    public ConsultaOfertaAdapter(ConsultaOfertaServiceExternal consultaOfertaServiceExternal) {
        this.consultaOfertaServiceExternal = consultaOfertaServiceExternal;
    }

    @Override
    public OfertaResponse getOfertaById(String id) {
        LOGGER.info("Chamada no servico externo de consultar oferta");
        return consultaOfertaServiceExternal.getOfertaById(id);
    }
}
