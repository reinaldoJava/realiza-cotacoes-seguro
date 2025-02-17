package com.realizacontacoes.realizacontacoes.seguro.config;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.service.OfertaValidator;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaOfertaServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ConsultaProdutoServicePort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.CotacaoRepositoryPort;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
@Configuration
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
    public ProcessaCotacaoUseCase processaCotacaoService(OfertaServiceUseCase ofertaServiceUseCase, ProdutoServiceUseCase produtoServiceUseCase,
                                                         CotacaoDataBaseUseCase cotacaoDataBaseUseCase, CotacaoProducerUseCase cotacaoProducerUseCase) {
        return new ProcessaCotacaoUseCase(ofertaServiceUseCase,produtoServiceUseCase,
                cotacaoDataBaseUseCase,cotacaoProducerUseCase, ofertaValidator());
    }
    @Bean
    public CotacaoDataBaseUseCase salvaCotacaoUseCase(CotacaoRepositoryPort cotacaoRepositoryPort){
        return new CotacaoDataBaseUseCase(cotacaoRepositoryPort);
    }

    @Bean
    @DependsOn("processaCotacaoService")
    public ProcessaCotacaoPort processaCotacaoPort(ProcessaCotacaoUseCase processaCotacaoUseCase) {
        return processaCotacaoUseCase;
    }
    @Bean
    public OfertaValidator ofertaValidator() {
        return new OfertaValidator();
    }
}
