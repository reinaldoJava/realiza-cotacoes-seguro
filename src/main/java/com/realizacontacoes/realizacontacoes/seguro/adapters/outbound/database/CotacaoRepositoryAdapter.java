package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.mapper.CotacaoMapper;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
public class CotacaoRepositoryAdapter implements CotacaoRepositoryPort {

    private CotacaoMapper cotacaoMapper = new CotacaoMapper();
    private final CotacaoJpaRepository cotacaoJpaRepository;

    public CotacaoRepositoryAdapter(CotacaoJpaRepository cotacaoJpaRepository) {
        this.cotacaoJpaRepository = cotacaoJpaRepository;
    }

    @Override
    public void atualizaCotacao(Cotacao cotacao) {
        this.cotacaoJpaRepository.save(cotacaoMapper.toEntity(cotacao));
    }

    @Override
    public Cotacao salvarCotacao(Cotacao cotacao) {
        return cotacaoMapper.toDomain(cotacaoJpaRepository.save(cotacaoMapper.toEntity(cotacao)));
    }
}
