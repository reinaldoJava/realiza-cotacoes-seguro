package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.avro.CotacaoAvro;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.RecebeCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoAvroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CotacaoConsumerUseCase implements RecebeCotacaoPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoConsumerUseCase.class);

    private final CotacaoRepositoryPort cotacaoRepositoryPort;


    public CotacaoConsumerUseCase(CotacaoRepositoryPort cotacaoRepositoryPort) {
        this.cotacaoRepositoryPort = cotacaoRepositoryPort;
    }


    @Override
    public void processarCotacao(CotacaoAvro response) {
        cotacaoRepositoryPort.atualizaCotacao(CotacaoAvroMapper.toResponseDTO(response));
    }
}
