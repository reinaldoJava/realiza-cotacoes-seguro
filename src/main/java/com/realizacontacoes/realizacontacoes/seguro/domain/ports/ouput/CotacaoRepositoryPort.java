package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;

public interface CotacaoRepositoryPort {

    void atualizaCotacao(CotacaoDTO cotacaoDTO);
    CotacaoDTO salvarCotacao(CotacaoDTO requisicao);

}

