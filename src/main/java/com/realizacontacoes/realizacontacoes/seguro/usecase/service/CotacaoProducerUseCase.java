package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;

public class CotacaoProducerUseCase {

    private final EnviaMensagemCotacaoPort enviaMensagemCotacaoPort;

    public CotacaoProducerUseCase(EnviaMensagemCotacaoPort enviaMensagemCotacaoPort) {
        this.enviaMensagemCotacaoPort = enviaMensagemCotacaoPort;
    }
    public void enviarMensagem(String mensagem){
        enviaMensagemCotacaoPort.enviarMensagem(mensagem);
    }

}
