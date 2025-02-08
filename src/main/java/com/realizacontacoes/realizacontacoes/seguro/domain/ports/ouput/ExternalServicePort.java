package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;

public interface ExternalServicePort {
    InsurancePolicy getExternalData(String id);
}
