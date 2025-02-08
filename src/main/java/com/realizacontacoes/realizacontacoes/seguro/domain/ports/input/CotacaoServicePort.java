package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsurancePolicy;

public interface CotacaoServicePort {
    InsurancePolicy getExternalData(String id);
}
