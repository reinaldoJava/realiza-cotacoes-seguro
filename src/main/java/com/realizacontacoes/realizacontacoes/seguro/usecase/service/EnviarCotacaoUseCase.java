package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.EnviaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;

public class EnviarCotacaoUseCase implements EnviaCotacaoPort {

    private final EnviaMensagemCotacaoPort enviaMensagemCotacaoPort;

    public EnviarCotacaoUseCase(EnviaMensagemCotacaoPort enviaMensagemCotacaoPort) {
        this.enviaMensagemCotacaoPort = enviaMensagemCotacaoPort;
    }

    @Override
    public void enviarCotacao(InsuranceRequest mensagem) {
        enviaMensagemCotacaoPort.enviarMensagem(mensagem.toString());
    }
}
