package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;

public class OfertaServiceUseCase{

    private final ConsultaOfertaServicePort consultaOfertaServicePort;

    public OfertaServiceUseCase(ConsultaOfertaServicePort consultaOfertaServicePort) {
        this.consultaOfertaServicePort = consultaOfertaServicePort;
    }
    public OfertaResponse getOferta(String id, String produtoId){
        OfertaResponse ofertaResponse = consultaOfertaServicePort.getOfertaById(id);
        ofertaResponse.validarAtividade();
        ofertaResponse.validarProdutoAssociado(produtoId);
        return ofertaResponse;
    }
}
