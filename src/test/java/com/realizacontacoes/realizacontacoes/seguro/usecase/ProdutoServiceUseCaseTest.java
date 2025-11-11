package com.realizacontacoes.realizacontacoes.seguro.usecase;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.realizacontacoes.realizacontacoes.seguro.utils.ProdutoResponseMock.createMockProdutoResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Teste da Classe ProdutoServiceUseCase")
public class ProdutoServiceUseCaseTest {

    @Mock
    private ConsultaProdutoServicePort consultaProdutoServicePort;

    @InjectMocks
    private ProdutoServiceUseCase produtoServiceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar produto quando ID for válido")
    void deveRetornarProdutoQuandoIdForValido() {
        // Arrange
        String id = "123";
         ProdutoResponse esperado = createMockProdutoResponse();
        when(consultaProdutoServicePort.consultarProduto(id)).thenReturn(esperado);

        // Act
        ProdutoResponse resultado = produtoServiceUseCase.getProduto(id);

        // Assert
        assertEquals(esperado, resultado);
        verify(consultaProdutoServicePort, times(1)).consultarProduto(id);
    }

    @Test
    @DisplayName("Deve retornar null quando ID não for encontrado")
    void deveRetornarNullQuandoIdNaoForEncontrado() {
        // Arrange
        String id = "999";
        when(consultaProdutoServicePort.consultarProduto(id)).thenReturn(null);

        // Act
        ProdutoResponse resultado = produtoServiceUseCase.getProduto(id);

        // Assert
        assertEquals(null, resultado);
        verify(consultaProdutoServicePort, times(1)).consultarProduto(id);
    }
}

