package com.realizacontacoes.realizacontacoes.seguro.usecase;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;

public class CotacaoProducerUseCase {

    private final EnviaMensagemCotacaoPort enviaMensagemCotacaoPort;

    public CotacaoProducerUseCase(EnviaMensagemCotacaoPort enviaMensagemCotacaoPort) {
        this.enviaMensagemCotacaoPort = enviaMensagemCotacaoPort;
    }
    public void enviarMensagem(Cotacao cotacao){
        enviaMensagemCotacaoPort.enviarCotacao(cotacao);
    }

}
