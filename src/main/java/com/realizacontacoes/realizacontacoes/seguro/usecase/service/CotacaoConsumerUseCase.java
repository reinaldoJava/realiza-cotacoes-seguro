package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;
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
    public void processarCotacao(CotacaoResponse response) {

        try {
            //TODO Converter esse objeto response para um objeto da tabela.
            //cotacaoRepositoryPort.salvarCotacao(new CotacaoResponse());

        } catch (Exception e) {
            LOGGER.error("Erro ao desserializar a cotação: {}", e.getMessage());
            throw new IllegalArgumentException("Formato inválido da mensagem Kafka.");
        }
    }
}
