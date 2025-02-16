package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.avro.CotacaoAvro;

public interface RecebeCotacaoPort {
    void processarCotacao(CotacaoAvro response);
}
