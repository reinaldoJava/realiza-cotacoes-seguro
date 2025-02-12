package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.ProdutoResponse;
import org.instancio.Instancio;
import org.instancio.Model;

import java.time.Instant;
import java.util.List;

import static org.instancio.Select.field;

public class ProdutoResponseMock {

    public static ProdutoResponse createMockProdutoResponse() {
        Model<ProdutoResponse> model = Instancio.of(ProdutoResponse.class)
                .set(field("id"), "123")
                .set(field("name"), "Mock Produto")
                .set(field("createdAt"), Instant.now())
                .set(field("active"), true)
                .set(field("ofertas"), List.of("Oferta 1", "Oferta 2"))
                .toModel();

        return Instancio.create(model);
    }
}

