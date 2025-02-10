package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;
import org.springframework.stereotype.Service;

@Service
public class ConsultaProdutoAdapter implements ConsultaProdutoServicePort {

    private final ConsultaProdutoServiceExternal consultaProdutoServiceExternal;

    public ConsultaProdutoAdapter(ConsultaProdutoServiceExternal consultaProdutoServiceExternal) {
        this.consultaProdutoServiceExternal = consultaProdutoServiceExternal;
    }

    @Override
    public ProdutoResponse consultarProduto(String id) {
        return consultaProdutoServiceExternal.getProdutoById(id);
    }
}
