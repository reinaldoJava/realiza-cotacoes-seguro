package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoMapper;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoMapperManual;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessaCotacaoUseCase implements ProcessaCotacaoPort {
    private final OfertaServiceUseCase ofertaServiceUseCase;
    private final ProdutoServiceUseCase produtoServiceUseCase;
    private final SalvaCotacaoUseCase salvaCotacaoUseCase;
    private final CotacaoProducerUseCase cotacaoProducerUseCase;
    @Autowired
    private CotacaoMapper cotacaoMapper;

    public ProcessaCotacaoUseCase(OfertaServiceUseCase ofertaServiceUseCase, ProdutoServiceUseCase produtoServiceUseCase, SalvaCotacaoUseCase salvaCotacaoUseCase, CotacaoProducerUseCase cotacaoProducerUseCase) {
        this.ofertaServiceUseCase = ofertaServiceUseCase;
        this.produtoServiceUseCase = produtoServiceUseCase;
        this.salvaCotacaoUseCase = salvaCotacaoUseCase;
        this.cotacaoProducerUseCase = cotacaoProducerUseCase;
    }


    @Override
    public void processarCotacao(InsuranceRequest request) {
        //Busca informações do produto.
        ProdutoResponse produtoResponse = produtoServiceUseCase.getProduto(request.productId());
        //Busca informações da oferta.
        //TODO Rever essa forma que esta sendo incluido esses 2 objetos.
        OfertaResponse ofertaResponse = ofertaServiceUseCase.getOferta(produtoResponse.ofertas().stream().findFirst().get(), produtoResponse.id());
        //Salva no banco
        CotacaoDTO cotacaoDTO = salvaCotacaoUseCase.salvarCotacao(cotacaoMapper.criarCotacao(produtoResponse,ofertaResponse,request));
        //Envia ao Kafka.
        cotacaoProducerUseCase.enviarMensagem(cotacaoDTO);
    }
}
