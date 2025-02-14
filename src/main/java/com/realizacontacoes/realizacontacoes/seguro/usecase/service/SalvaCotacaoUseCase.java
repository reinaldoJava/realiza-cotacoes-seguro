package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;

public class SalvaCotacaoUseCase {

    private final CotacaoRepositoryPort cotacaoRepositoryPort;

    public SalvaCotacaoUseCase(CotacaoRepositoryPort cotacaoRepositoryPort) {
        this.cotacaoRepositoryPort = cotacaoRepositoryPort;
    }

    public CotacaoDTO salvarCotacao(CotacaoDTO requisicao) {
        return cotacaoRepositoryPort.salvarCotacao(requisicao);
    }
}
