package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;

public interface ProcessaCotacaoPort {
    void processarCotacao(InsuranceRequest requisicao);
}
