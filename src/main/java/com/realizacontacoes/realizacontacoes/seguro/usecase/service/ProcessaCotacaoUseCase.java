package com.realizacontacoes.realizacontacoes.seguro.usecase.service;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.mapper.CotacaoMapper;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;

public class ProcessaCotacaoUseCase implements ProcessaCotacaoPort {
    private final OfertaServiceUseCase ofertaServiceUseCase;
    private final ProdutoServiceUseCase produtoServiceUseCase;
    private final SalvaCotacaoUseCase salvaCotacaoUseCase;
    private final CotacaoProducerUseCase cotacaoProducerUseCase;

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
        OfertaResponse ofertaResponse = ofertaServiceUseCase.getOferta(produtoResponse.ofertas().stream().findFirst().get(), produtoResponse.id());
        //Salva no banco
        Cotacao cotacao = salvaCotacaoUseCase.salvarCotacao(CotacaoMapper.criarCotacao(produtoResponse,ofertaResponse,request));
        //Envia ao Kafka.
        cotacaoProducerUseCase.enviarMensagem(cotacao.toString());
    }
}
