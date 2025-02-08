package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.CotacaoServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ExternalServicePort;

public class CotacaoServiceUseCase implements CotacaoServicePort {

    private final ExternalServicePort externalServicePort;

    public CotacaoServiceUseCase(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }

    @Override
    public InsurancePolicy getExternalData(String id) {
        return externalServicePort.getExternalData(id);
    }
}
