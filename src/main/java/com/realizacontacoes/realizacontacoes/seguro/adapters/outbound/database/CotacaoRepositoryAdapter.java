package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CotacaoRepositoryAdapter implements CotacaoRepositoryPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoRepositoryAdapter.class);

    private final CotacaoJpaRepository cotacaoJpaRepository;

    private final CotacaoMapper cotacaoMapper;

    public CotacaoRepositoryAdapter(CotacaoJpaRepository cotacaoJpaRepository, CotacaoMapper cotacaoMapper) {
        this.cotacaoJpaRepository = cotacaoJpaRepository;
        this.cotacaoMapper = cotacaoMapper;
    }

    @Override
    public void atualizaCotacao(CotacaoDTO cotacaoDTO) {
        LOGGER.info("Atualizacao da cotacao no banco de dados");
        this.cotacaoJpaRepository.save(cotacaoMapper.toEntity(cotacaoDTO));
    }

    @Override
    public CotacaoDTO salvarCotacao(CotacaoDTO cotacaoDTO) {
        LOGGER.info("Salva da cotacao no banco de dados");
        return cotacaoMapper.toDomain(cotacaoJpaRepository.save(cotacaoMapper.toEntity(cotacaoDTO)));
    }
}
