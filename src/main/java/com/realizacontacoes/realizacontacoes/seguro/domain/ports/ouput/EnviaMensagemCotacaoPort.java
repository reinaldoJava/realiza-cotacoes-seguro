package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

public interface EnviaMensagemCotacaoPort {
    void enviarMensagem(String topic, String mensagem);
}
