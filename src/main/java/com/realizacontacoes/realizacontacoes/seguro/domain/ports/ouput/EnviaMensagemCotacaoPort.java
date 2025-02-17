package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;

public interface EnviaMensagemCotacaoPort {
    void enviarCotacao(Cotacao cotacao);
}
