package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProductResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.CotacaoServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ExternalServicePort;
import com.realizacontacoes.realizacontacoes.seguro.usecase.validacao.ValidaCotacaoUseCase;

public class CotacaoServiceUseCase implements CotacaoServicePort {

    private final ExternalServicePort externalServicePort;
    private final ValidaCotacaoUseCase validaCotacaoUseCase;

    public CotacaoServiceUseCase(ExternalServicePort externalServicePort, ValidaCotacaoUseCase validaCotacaoUseCase) {
        this.externalServicePort = externalServicePort;
        this.validaCotacaoUseCase = validaCotacaoUseCase;
    }

    @Override
    public ProductResponse getExternalData(String id) {
        return externalServicePort.getExternalData(id);
    }
    public void validarCotacao(InsuranceRequest request, ProductResponse product) {
        validaCotacaoUseCase.ValidaCotacao(request, product);
    }
}
