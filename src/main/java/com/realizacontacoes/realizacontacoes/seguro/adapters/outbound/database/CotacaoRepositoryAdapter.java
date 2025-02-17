package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.CotacaoNaoEncontradaException;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CotacaoRepositoryAdapter implements CotacaoRepositoryPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoRepositoryAdapter.class);

    private final CotacaoJpaRepository cotacaoJpaRepository;

    public CotacaoRepositoryAdapter(CotacaoJpaRepository cotacaoJpaRepository) {
        this.cotacaoJpaRepository = cotacaoJpaRepository;

    }

    @Override
    public void atualizaCotacao(Cotacao cotacao) {
        LOGGER.info("Atualizacao da cotacao no banco de dados");
        //TODO chumbei o valor do policy aqui pq tentei inlcuir direto no consumer do kafka.
        // Mas pelo que vi teria q criar uma imagem docker do ksql conectado ao topic do kafka e criar uma query q faria essa atualizacao.
        CotacaoEntity entity = CotacaoEntityMapper.toEntity(cotacao);
        entity.setInsurancePolicyId("756969");
        this.cotacaoJpaRepository.save(entity);
    }

    @Override
    public Cotacao salvarCotacao(Cotacao cotacao) {
        LOGGER.info("Salva da cotacao no banco de dados");
        return CotacaoEntityMapper.toDomain(cotacaoJpaRepository.save(CotacaoEntityMapper.toEntity(cotacao)));
    }

    public Cotacao buscaCotacaoPorId(Long id) {
        return cotacaoJpaRepository.findById(id)
                .map(CotacaoEntityMapper::toDomain)
                .orElseThrow(() -> new CotacaoNaoEncontradaException("Cotação não encontrada com ID: "+ id));
    }
}
