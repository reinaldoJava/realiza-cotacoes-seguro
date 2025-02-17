package com.realizacontacoes.realizacontacoes.seguro.usecase;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;

public class CotacaoDataBaseUseCase {

    private final CotacaoRepositoryPort cotacaoRepositoryPort;

    public CotacaoDataBaseUseCase(CotacaoRepositoryPort cotacaoRepositoryPort) {
        this.cotacaoRepositoryPort = cotacaoRepositoryPort;
    }

    public Cotacao salvarCotacao(Cotacao requisicao) {
        return cotacaoRepositoryPort.salvarCotacao(requisicao);
    }
    public Cotacao buscaCotacaoPorId(Long id){
        return  cotacaoRepositoryPort.buscaCotacaoPorId(id);
    }
}
