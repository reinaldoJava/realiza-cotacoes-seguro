package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;

public interface RecebeCotacaoPort {
    void processarCotacao(CotacaoResponse response);
}
