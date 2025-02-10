package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
public class CotacaoRepositoryAdapter implements CotacaoRepositoryPort {

    private final CotacaoJpaRepository cotacaoJpaRepository;

    public CotacaoRepositoryAdapter(CotacaoJpaRepository cotacaoJpaRepository) {
        this.cotacaoJpaRepository = cotacaoJpaRepository;
    }

    @Override
    public void atualizaCotacao(InsuranceRequest cotacao) {
        this.cotacaoJpaRepository.save(cotacao);
    }

    @Override
    public InsuranceRequest salvarCotacao(InsuranceRequest requisicao) {
        return cotacaoJpaRepository.save(requisicao);
    }
}
