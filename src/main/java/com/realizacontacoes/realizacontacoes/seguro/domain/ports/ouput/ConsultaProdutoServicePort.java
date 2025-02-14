package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;

public interface ConsultaProdutoServicePort {
    ProdutoResponse consultarProduto(String id);
}
