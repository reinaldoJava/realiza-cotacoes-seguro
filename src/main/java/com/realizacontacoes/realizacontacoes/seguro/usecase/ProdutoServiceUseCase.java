package com.realizacontacoes.realizacontacoes.seguro.usecase;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;

public class ProdutoServiceUseCase  {

    private final ConsultaProdutoServicePort consultaProdutoServicePort;

    public ProdutoServiceUseCase(ConsultaProdutoServicePort consultaProdutoServicePort) {
        this.consultaProdutoServicePort = consultaProdutoServicePort;
    }

    public ProdutoResponse getProduto(String id) {
        return consultaProdutoServicePort.consultarProduto(id);
    }

}
