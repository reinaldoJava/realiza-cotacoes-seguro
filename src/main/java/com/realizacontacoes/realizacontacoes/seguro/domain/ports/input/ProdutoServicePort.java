package com.realizacontacoes.realizacontacoes.seguro.domain.ports.input;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProdutoResponse;

public interface ProdutoServicePort {
    ProdutoResponse getProduto(String id);
}
