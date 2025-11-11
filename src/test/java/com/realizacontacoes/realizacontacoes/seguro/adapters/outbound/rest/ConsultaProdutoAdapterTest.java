package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.utils.ProdutoResponseMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaProdutoAdapterTest {

    @Mock
    private ConsultaProdutoServiceExternal consultaProdutoServiceExternal;

    @InjectMocks
    private ConsultaProdutoAdapter consultaProdutoAdapter;

    @Test
    @DisplayName("Deve consultar o produto com sucesso")
    void consultarProdutoComSucesso() {
        String id = "123";
        ProdutoResponse produtoResponse = ProdutoResponseMock.createMockProdutoResponse();

        when(consultaProdutoServiceExternal.getProdutoById(id)).thenReturn(produtoResponse);

        ProdutoResponse resultado = consultaProdutoAdapter.consultarProduto(id);

        assertEquals(produtoResponse, resultado);

        verify(consultaProdutoServiceExternal, times(1)).getProdutoById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao consultar o produto com ID inválido")
    void consultarProdutoComIdInvalido() {
        String id = "123";
        when(consultaProdutoServiceExternal.getProdutoById(id)).thenThrow(new RuntimeException("Erro ao consultar o produto"));

        assertThrows(RuntimeException.class, () -> consultaProdutoAdapter.consultarProduto(id));

        verify(consultaProdutoServiceExternal, times(1)).getProdutoById(id);
    }
}
