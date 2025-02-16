package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CoveragesMapper.class})
public interface CotacaoMapper {

    @Mapping(source = "customerDTO", target = "customer")
    @Mapping(source = "coverages", target = "coverages")
    CotacaoEntity toEntity(CotacaoDTO dto);

    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(source = "coverages", target = "coverages")
    CotacaoDTO toDomain(CotacaoEntity entity);

    @Mapping(source = "produtoResponse.id", target = "productId")
    @Mapping(source = "ofertaResponse.id", target = "offerId")
    @Mapping(source = "insuranceRequest.category", target = "category")
    @Mapping(source = "insuranceRequest.totalMonthlyPremiumAmount", target = "totalMonthlyPremiumAmount")
    @Mapping(source = "insuranceRequest.totalCoverageAmount", target = "totalCoverageAmount")
    @Mapping(source = "insuranceRequest.assistances", target = "assistances")
    @Mapping(source = "ofertaResponse.coverages", target = "coverages")
    @Mapping(source = "insuranceRequest.customer", target = "customerDTO")
    @Mapping(target = "id", ignore = true) // ID será gerado posteriormente
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now().toString())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().toString())")
    CotacaoDTO criarCotacao(ProdutoResponse produtoResponse, OfertaResponse ofertaResponse, InsuranceRequest insuranceRequest);

    // Após o mapeamento, configurar a relação bidirecional
    @AfterMapping
    default void setCotacaoReference(@MappingTarget CotacaoEntity cotacaoEntity) {
        if (cotacaoEntity.getCoverages() != null) {
            cotacaoEntity.getCoverages().forEach(coverage -> coverage.setCotacao(cotacaoEntity));
        }
    }
}