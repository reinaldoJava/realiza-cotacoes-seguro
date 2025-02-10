package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProdutoResponse;

public interface ConsultaProdutoServicePort {
    ProdutoResponse consultarProduto(String id);
}
