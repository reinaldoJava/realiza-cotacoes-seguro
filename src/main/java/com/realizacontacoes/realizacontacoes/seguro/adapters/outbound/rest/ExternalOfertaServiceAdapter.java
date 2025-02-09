package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.OfertaServicePort;

public class ExternalOfertaServiceAdapter implements OfertaServicePort {

    private final ExternalOfertaService externalOfertaService;

    public ExternalOfertaServiceAdapter(ExternalOfertaService externalOfertaService) {
        this.externalOfertaService = externalOfertaService;
    }

    @Override
    public OfertaResponse getOfertaById(String id) {

        return externalOfertaService.getOfferById(id);
    }
}
