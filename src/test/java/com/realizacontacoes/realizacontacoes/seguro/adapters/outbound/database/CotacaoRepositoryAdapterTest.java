package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.CotacaoNaoEncontradaException;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CustomerEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.utils.CotacaoMock;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CotacaoRepositoryAdapterTest {

    @Mock
    private CotacaoJpaRepository cotacaoJpaRepository;

    @Mock
    private CotacaoEntityMapper cotacaoEntityMapper;

    @InjectMocks
    private CotacaoRepositoryAdapter cotacaoRepositoryAdapter;

    Cotacao cotacao = CotacaoMock.createMockCotacao();
    CotacaoEntity entity = CotacaoEntityMapper.toEntity(cotacao);
    CustomerEntity customerEntity = new CustomerEntity();

    @BeforeEach
    void setup() {
        entity.setCustomer(customerEntity);
    }

    @Test
    @DisplayName("Deve atualizar a cotação no banco de dados")
    void atualizaCotacao() {
        when(cotacaoJpaRepository.save(any(CotacaoEntity.class))).thenReturn(entity);
        cotacaoRepositoryAdapter.atualizaCotacao(cotacao);
        verify(cotacaoJpaRepository, times(1)).save(any(CotacaoEntity.class));
    }

    @Test
    @DisplayName("Deve salvar a cotação no banco de dados")
    void salvarCotacao() {

        Cotacao cotacaoRetorno = CotacaoMock.createMockCotacao();
        when(cotacaoJpaRepository.save(any(CotacaoEntity.class))).thenReturn(entity);
        Cotacao resultado = cotacaoRepositoryAdapter.salvarCotacao(cotacao);
        assertEquals(cotacaoRetorno.productId(), resultado.productId());
        verify(cotacaoJpaRepository, times(1)).save(any(CotacaoEntity.class));
    }

    @Test
    @DisplayName("Deve buscar a cotação por ID")
    void buscaCotacaoPorId() {
        Long id = 1L;
        when(cotacaoJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        Cotacao resultado = cotacaoRepositoryAdapter.buscaCotacaoPorId(id);
        assertEquals(cotacao.productId(), resultado.productId());
        verify(cotacaoJpaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar cotação por ID inexistente")
    void buscaCotacaoPorIdInexistente() {
        Long id = 1L;
        when(cotacaoJpaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CotacaoNaoEncontradaException.class, () -> cotacaoRepositoryAdapter.buscaCotacaoPorId(id));
        verify(cotacaoJpaRepository, times(1)).findById(id);
    }
}