package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;

public interface ConsultaOfertaServicePort {
    OfertaResponse getOfertaById(String id);
}
