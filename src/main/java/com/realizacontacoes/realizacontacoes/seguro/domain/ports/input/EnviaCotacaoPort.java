package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;

public interface EnviaCotacaoPort {
    void enviarCotacao(InsuranceRequest mensagem);
}
