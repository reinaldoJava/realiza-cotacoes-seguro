package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.avro.CotacaoRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;

public interface EnviaMensagemCotacaoPort {
    void enviarCotacao(String topic, CotacaoDTO cotacaoDTO);
}
