package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsultaProdutoAdapter implements ConsultaProdutoServicePort {

    private final ConsultaProdutoServiceExternal consultaProdutoServiceExternal;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaProdutoAdapter.class);

    public ConsultaProdutoAdapter(ConsultaProdutoServiceExternal consultaProdutoServiceExternal) {
        this.consultaProdutoServiceExternal = consultaProdutoServiceExternal;
    }

    @Override
    public ProdutoResponse consultarProduto(String id) {
        LOGGER.info("Chamada no servico externo de consultar Produto");
        return consultaProdutoServiceExternal.getProdutoById(id);
    }
}
