package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;
import org.springframework.beans.factory.annotation.Value;

public class CotacaoProducerUseCase {
    //TODO Verificar se existe opcao melhor
    @Value("${spring.kafka.producer.topic}")
    private String topic;
    private final EnviaMensagemCotacaoPort enviaMensagemCotacaoPort;

    public CotacaoProducerUseCase(EnviaMensagemCotacaoPort enviaMensagemCotacaoPort) {
        this.enviaMensagemCotacaoPort = enviaMensagemCotacaoPort;
    }
    public void enviarMensagem(CotacaoDTO cotacaoDTO){
        enviaMensagemCotacaoPort.enviarCotacao(topic, cotacaoDTO);
    }

}
