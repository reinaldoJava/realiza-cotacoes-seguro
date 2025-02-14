package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoMapper;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CotacaoRepositoryAdapter implements CotacaoRepositoryPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoRepositoryAdapter.class);

    private CotacaoMapper cotacaoMapper = new CotacaoMapper();

    private final CotacaoJpaRepository cotacaoJpaRepository;

    public CotacaoRepositoryAdapter(CotacaoJpaRepository cotacaoJpaRepository) {
        this.cotacaoJpaRepository = cotacaoJpaRepository;
    }

    @Override
    public void atualizaCotacao(CotacaoDTO cotacaoDTO) {
        LOGGER.info("Atualizacao da cotacao no banco de dados");
        this.cotacaoJpaRepository.save(cotacaoMapper.toEntity(cotacaoDTO));
    }

    @Override
    public CotacaoDTO salvarCotacao(CotacaoDTO cotacaoDTO) {
        LOGGER.info("Salva da cotacao no banco de dados");
        return cotacaoMapper.toDomain(cotacaoJpaRepository.save(CotacaoMapper.toEntity(cotacaoDTO)));
    }
}
