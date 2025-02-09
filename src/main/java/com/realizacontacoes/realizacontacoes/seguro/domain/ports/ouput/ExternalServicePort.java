package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProductResponse;

public interface ExternalServicePort {
    ProductResponse getExternalData(String id);
}
