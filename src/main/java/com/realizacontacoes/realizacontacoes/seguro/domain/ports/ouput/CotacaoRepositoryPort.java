package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;

public interface CotacaoRepositoryPort {
    void atualizaCotacao(InsuranceRequest cotacao);
    InsuranceRequest salvarCotacao(InsuranceRequest requisicao);

}

