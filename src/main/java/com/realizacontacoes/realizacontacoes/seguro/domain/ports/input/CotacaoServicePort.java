package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProductResponse;

public interface CotacaoServicePort {
    ProductResponse getExternalData(String id);
    public void validarCotacao(InsuranceRequest request, ProductResponse product);
}
