package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.OfertaResponse;

public interface OfertaServicePort {
    OfertaResponse getOferta(String id);
}
