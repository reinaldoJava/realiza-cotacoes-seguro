/*
package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.utils.CotacaoMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@Import(CotacaoRepositoryAdapter.class)
class CotacaoRepositoryAdapterTest {

    @MockitoBean
    private CotacaoJpaRepository cotacaoJpaRepository;

    @Autowired
    private CotacaoRepositoryAdapter cotacaoRepositoryAdapter;

    @Test
    @DisplayName("Deve salvar cotação corretamente")
    void deveSalvarCotacaoCorretamente() {
        // Arrange
        Cotacao cotacaoMock = CotacaoMock.createMockCotacao();
        when(cotacaoJpaRepository.save(any())).thenReturn(cotacaoMock);

        // Act
        Cotacao resultado = cotacaoRepositoryAdapter.salvarCotacao(cotacaoMock);

        // Assert
        assertThat(resultado).isEqualTo(cotacaoMock);
        verify(cotacaoJpaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve atualizar cotação corretamente")
    void deveAtualizarCotacaoCorretamente() {
        // Arrange
        Cotacao cotacaoMock = CotacaoMock.createMockCotacao();
        when(cotacaoJpaRepository.save(any())).thenReturn(cotacaoMock);

        // Act
        cotacaoRepositoryAdapter.atualizaCotacao(cotacaoMock);

        // Assert
        verify(cotacaoJpaRepository, times(1)).save(any());
    }
}
*/
