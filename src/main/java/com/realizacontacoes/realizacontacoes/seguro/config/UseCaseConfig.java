package com.realizacontacoes.realizacontacoes.seguro.config;

import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.usecase.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

public class UseCaseConfig {

    @Bean
    public OfertaServiceUseCase cotacaoService(ConsultaOfertaServicePort consultaOfertaServicePort) {
        return new OfertaServiceUseCase(consultaOfertaServicePort);
    }

    @Bean
    public ProdutoServiceUseCase produtoService(ConsultaProdutoServicePort consultaProdutoServicePort) {
        return new ProdutoServiceUseCase(consultaProdutoServicePort);
    }
    @Bean
    public CotacaoConsumerUseCase cotacaoConsumerService(CotacaoRepositoryPort cotacaoRepositoryPort) {
        return new CotacaoConsumerUseCase(cotacaoRepositoryPort);
    }
    @Bean
    public CotacaoProducerUseCase cotataoProducerService(EnviaMensagemCotacaoPort enviaMensagemCotacaoPort) {
        return new CotacaoProducerUseCase(enviaMensagemCotacaoPort);
    }
    @Bean
    public ProcessaCotacaoUseCase processaCotacaoService(OfertaServiceUseCase ofertaServiceUseCase, ProdutoServiceUseCase produtoServiceUseCase, SalvaCotacaoUseCase salvaCotacaoUseCase, CotacaoProducerUseCase cotacaoProducerUseCase) {
        return new ProcessaCotacaoUseCase(ofertaServiceUseCase,produtoServiceUseCase,salvaCotacaoUseCase,cotacaoProducerUseCase);
    }

    @Bean
    @DependsOn("processaCotacaoService")
    public ProcessaCotacaoPort processaCotacaoPort(ProcessaCotacaoUseCase processaCotacaoUseCase) {
        return processaCotacaoUseCase;
    }
}
