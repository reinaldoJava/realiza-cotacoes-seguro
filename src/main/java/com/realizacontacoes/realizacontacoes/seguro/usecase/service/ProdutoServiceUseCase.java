package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProdutoServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;

public class ProdutoServiceUseCase implements ProdutoServicePort {

    private final ConsultaProdutoServicePort consultaProdutoServicePort;

    public ProdutoServiceUseCase(ConsultaProdutoServicePort consultaProdutoServicePort) {
        this.consultaProdutoServicePort = consultaProdutoServicePort;
    }

    @Override
    public ProdutoResponse getProduto(String id) {
        return consultaProdutoServicePort.consultarProduto(id);
    }

}
