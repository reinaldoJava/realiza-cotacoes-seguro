package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;
import com.realizacontacoes.realizacontacoes.seguro.utils.OfertaResponseMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfertaResponseTest {

    private final OfertaResponse oferta = OfertaResponseMock.criarOfertaResponseAtiva();
    @Test
    @DisplayName("Validar oferta ativa")
    public void testValidarAtividadeOfertaAtivaNaoDeveLancarExcecao() {
        oferta.validarAtividade();
    }

    @Test
    @DisplayName("Validar oferta inativa")
    public void testValidarAtividadeOfertaInativaDeveLancarExcecao() {
        OfertaResponse oferta = OfertaResponseMock.criarOfertaResponseInativa();
        assertThrows(ValidationException.class, oferta::validarAtividade);
    }

    @Test
    @DisplayName("Validar produto associado")
    public void testValidarProdutoAssociadoProdutoAssociadoNaoDeveLancarExcecao() {
        oferta.validarProdutoAssociado(oferta.productId());
    }

    @Test
    @DisplayName("Validar produto não associado")
    public void testValidarProdutoAssociadoProdutoNaoAssociadoDeveLancarExcecao() {
        OfertaResponse oferta = OfertaResponseMock.criarOfertaResponseComProdutoDiferente();
        assertThrows(ValidationException.class, () -> oferta.validarProdutoAssociado("produto-id-diferente"));
    }
}
