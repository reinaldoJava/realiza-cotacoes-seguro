package com.realizacontacoes.realizacontacoes.seguro.usecase;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest.ConsultaProdutoAdapter;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.service.OfertaValidator;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessaCotacaoUseCase implements ProcessaCotacaoPort {

    private final OfertaServiceUseCase ofertaServiceUseCase;
    private final ProdutoServiceUseCase produtoServiceUseCase;
    private final OfertaValidator ofertaValidator;
    private final CotacaoDataBaseUseCase cotacaoDataBaseUseCase;
    private final CotacaoProducerUseCase cotacaoProducerUseCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessaCotacaoUseCase.class);


    public ProcessaCotacaoUseCase(OfertaServiceUseCase ofertaServiceUseCase, ProdutoServiceUseCase produtoServiceUseCase,
                                  CotacaoDataBaseUseCase cotacaoDataBaseUseCase, CotacaoProducerUseCase cotacaoProducerUseCase,
                                  OfertaValidator ofertaValidator) {
        this.ofertaServiceUseCase = ofertaServiceUseCase;
        this.produtoServiceUseCase = produtoServiceUseCase;
        this.cotacaoDataBaseUseCase = cotacaoDataBaseUseCase;
        this.cotacaoProducerUseCase = cotacaoProducerUseCase;
        this.ofertaValidator = ofertaValidator;
    }


    @Override
    public void processarCotacao(InsuranceRequest request) {
        try {
            //Busca informações do produto.
            ProdutoResponse produtoResponse = produtoServiceUseCase.getProduto(request.productId());
            //Busca informações da oferta.
            //TODO Validar esse se a aferta existe.
            OfertaResponse ofertaResponse = ofertaServiceUseCase.getOferta(produtoResponse.ofertas().stream().findFirst().get());
            //TODO Rever todas as validacoes.
            //Validacoes de negocio
            ofertaValidator.validarOfertaAtiva(ofertaResponse);
            ofertaValidator.validarCoberturas(ofertaResponse,request.coverages());
            ofertaValidator.validarAssistencias(ofertaResponse, request.assistances());
            ofertaValidator.validarPremioMensal(ofertaResponse,request.totalMonthlyPremiumAmount());
            ofertaValidator.validarValorTotalCoberturas(request.coverages(),request.totalCoverageAmount());
            //Salva no banco
            Cotacao cotacao = cotacaoDataBaseUseCase.salvarCotacao(CotacaoEntityMapper.criarCotacao(produtoResponse,ofertaResponse,request));
            //Envia ao Kafka.
            cotacaoProducerUseCase.enviarMensagem(cotacao);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Cotacao buscarCotacaoPorId(Long id) {
        return cotacaoDataBaseUseCase.buscaCotacaoPorId(id);
    }
}
