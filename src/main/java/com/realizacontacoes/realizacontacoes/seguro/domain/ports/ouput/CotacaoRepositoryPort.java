package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;

public interface CotacaoRepositoryPort {

    void atualizaCotacao(CotacaoResponse response);
    CotacaoDTO salvarCotacao(CotacaoDTO requisicao);

}

