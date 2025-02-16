package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.RecebeCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CotacaoConsumerUseCase implements RecebeCotacaoPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoConsumerUseCase.class);

    private final CotacaoRepositoryPort cotacaoRepositoryPort;
    private final CotacaoMapper cotacaoMapper;

    public CotacaoConsumerUseCase(CotacaoRepositoryPort cotacaoRepositoryPort, CotacaoMapper cotacaoMapper) {
        this.cotacaoRepositoryPort = cotacaoRepositoryPort;
        this.cotacaoMapper = cotacaoMapper;
    }


    @Override
    public void processarCotacao(CotacaoResponse response) {

        try {
            cotacaoRepositoryPort.salvarCotacao(response);

        } catch (Exception e) {
            LOGGER.error("Erro ao desserializar a cotação: {}", e.getMessage());
            throw new IllegalArgumentException("Formato inválido da mensagem Kafka.");
        }
    }
}
