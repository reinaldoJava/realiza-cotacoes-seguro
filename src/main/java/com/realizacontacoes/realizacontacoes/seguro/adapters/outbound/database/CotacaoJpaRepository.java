package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CotacaoJpaRepository extends JpaRepository<CotacaoEntity, Long> {
}
