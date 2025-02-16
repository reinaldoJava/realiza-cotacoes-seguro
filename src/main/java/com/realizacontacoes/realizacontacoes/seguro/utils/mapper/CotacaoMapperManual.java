package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CoveragesEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CustomerEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.CustomerRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO Refatorar para MapStruct
public class CotacaoMapperManual {
    public static CotacaoEntity toEntity(CotacaoDTO dto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setDocumentNumber(dto.customerDTO().documentNumber());
        customerEntity.setName(dto.customerDTO().name());
        customerEntity.setType(dto.customerDTO().customerType());
        customerEntity.setGender(dto.customerDTO().gender());
        customerEntity.setDateOfBirth(dto.customerDTO().dateOfBirth());
        customerEntity.setEmail(dto.customerDTO().email());
        customerEntity.setPhoneNumber(dto.customerDTO().phoneNumber());

        List<CoveragesEntity> coveragesEntities = dto.coverages().entrySet().stream()
                .map(entry -> {
                    CoveragesEntity coverageEntity = new CoveragesEntity();
                    coverageEntity.setType(entry.getKey());
                    coverageEntity.setAmount(BigDecimal.valueOf(entry.getValue()));
                    return coverageEntity;
                }).collect(Collectors.toList());

        CotacaoEntity entity = new CotacaoEntity();
        entity.setId(dto.id());
        entity.setProductId(dto.productId());
        entity.setOfferId(dto.offerId());
        entity.setCategory(dto.category());
        entity.setTotalMonthlyPremiumAmount(dto.totalMonthlyPremiumAmount());
        entity.setTotalCoverageAmount(dto.totalCoverageAmount());
        entity.setCoverages(coveragesEntities);
        entity.setAssistances(dto.assistances());
        entity.setCustomer(customerEntity);
        entity.setUpdatedAt(LocalDateTime.parse(dto.updatedAt()));

        // Configurar a relação bidirecional
        coveragesEntities.forEach(coverage -> coverage.setCotacao(entity));
        customerEntity.setCotacao(entity);

        return entity;
    }

    public static CotacaoDTO toDomain(CotacaoEntity entity) {
        CustomerDTO customerDTO = new CustomerDTO(
                entity.getCustomer().getId(),
                entity.getCustomer().getDocumentNumber(),
                entity.getCustomer().getName(),
                entity.getCustomer().getType(),
                entity.getCustomer().getGender(),
                entity.getCustomer().getDateOfBirth(),
                entity.getCustomer().getEmail(),
                entity.getCustomer().getPhoneNumber()
        );

        Map<String, Double> coverages = entity.getCoverages().stream()
                .collect(Collectors.toMap(
                        CoveragesEntity::getType,
                        coveragesEntity -> coveragesEntity.getAmount().doubleValue()
                ));

        List<String> assistances = entity.getAssistances();

        return new CotacaoDTO(
                entity.getId(),
                entity.getProductId(),
                entity.getOfferId(),
                entity.getCategory(),
                entity.getTotalMonthlyPremiumAmount(),
                entity.getTotalCoverageAmount(),
                entity.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                entity.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                assistances,
                coverages,
                customerDTO
        );
    }
    public static CotacaoDTO criarCotacao(ProdutoResponse produtoResponse, OfertaResponse ofertaResponse, InsuranceRequest insuranceRequest) {
        // Criando o objeto Customer
        CustomerRequest customerRequest = insuranceRequest.customer();
        CustomerDTO customerDTO = new CustomerDTO(
                null,
                customerRequest.documentNumber(),
                customerRequest.name(),
                customerRequest.customerType(),
                customerRequest.gender(),
                LocalDate.parse(customerRequest.dateOfBirth()),
                customerRequest.email(),
                customerRequest.phoneNumber()
        );

        // Criando e retornando o objeto Cotacao
        return new CotacaoDTO(
                null,
                produtoResponse.id(),
                ofertaResponse.id(),
                insuranceRequest.category(),
                insuranceRequest.totalMonthlyPremiumAmount(),
                insuranceRequest.totalCoverageAmount(),
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                insuranceRequest.assistances(),
                ofertaResponse.coverages(),
                customerDTO

        );
    }
}
