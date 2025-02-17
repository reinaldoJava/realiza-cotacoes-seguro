package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;

public interface CotacaoRepositoryPort {

    void atualizaCotacao(Cotacao requisicao);
    Cotacao salvarCotacao(Cotacao requisicao);
    Cotacao buscaCotacaoPorId(Long id);


}

