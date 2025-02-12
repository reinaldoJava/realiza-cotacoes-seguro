package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;

public interface CotacaoRepositoryPort {

    void atualizaCotacao(Cotacao cotacao);
    Cotacao salvarCotacao(Cotacao requisicao);

}

