package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
//TODO colocar a entidade correta
public interface CotacaoJpaRepository extends JpaRepository<InsuranceRequest, Long> {
}
