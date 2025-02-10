package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.RecebeCotacaoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CotacaoConsumerUseCase implements RecebeCotacaoPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoConsumerUseCase.class);

    private final CotacaoRepositoryPort cotacaoRepositoryPort;

    public CotacaoConsumerUseCase(CotacaoRepositoryPort cotacaoRepositoryPort) {
        this.cotacaoRepositoryPort = cotacaoRepositoryPort;
    }


    @Override
    public void processarCotacao(String mensagem) {
        LOGGER.info("Processando cotação recebida: {}", mensagem);
        try {
            //TODO Fazer o parse do json para objeto
            //cotacaoRepositoryPort.salvarCotacao(new InsuranceRequest());

        } catch (Exception e) {
            LOGGER.error("Erro ao desserializar a cotação: {}", e.getMessage());
            throw new IllegalArgumentException("Formato inválido da mensagem Kafka.");
        }
    }
}
