package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.ProdutoResponse;
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
