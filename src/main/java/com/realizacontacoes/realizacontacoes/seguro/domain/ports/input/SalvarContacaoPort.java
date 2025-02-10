package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;

public interface SalvarContacaoPort {
    void processarCotacao(InsuranceRequest requisicao);
}
