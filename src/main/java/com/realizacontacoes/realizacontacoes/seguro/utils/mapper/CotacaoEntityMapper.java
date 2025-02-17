package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CoveragesEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CustomerEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Customer;
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
import java.util.Optional;
import java.util.stream.Collectors;
//TODO Criar jeito de implementar a atualizacao do updatedAt
public class CotacaoEntityMapper {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    public static CotacaoEntity toEntity(Cotacao dto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setDocumentNumber(dto.customer().documentNumber());
        customerEntity.setName(dto.customer().name());
        customerEntity.setType(dto.customer().type());
        customerEntity.setGender(dto.customer().gender());
        customerEntity.setDateOfBirth(dto.customer().dateOfBirth());
        customerEntity.setEmail(dto.customer().email());
        customerEntity.setPhoneNumber(dto.customer().phoneNumber());

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

    public static Cotacao toDomain(CotacaoEntity entity) {
        Customer customer = new Customer(
                Optional.ofNullable(entity.getCustomer().getId()),
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

        return new Cotacao(
                entity.getId(),
                entity.getProductId(),
                entity.getInsurancePolicyId() == null ? "" :entity.getInsurancePolicyId(),
                entity.getOfferId(),
                entity.getCategory(),
                entity.getTotalMonthlyPremiumAmount(),
                entity.getTotalCoverageAmount(),
                entity.getCreatedAt().format(dateTimeFormatter),
                entity.getUpdatedAt().format(dateTimeFormatter),
                assistances,
                coverages,
                customer
        );
    }
    public static Cotacao criarCotacao(ProdutoResponse produtoResponse, OfertaResponse ofertaResponse, InsuranceRequest insuranceRequest) {
        CustomerRequest customerRequest = insuranceRequest.customer();
        Customer customer = new Customer(
                null,
                customerRequest.documentNumber(),
                customerRequest.name(),
                customerRequest.type(),
                customerRequest.gender(),
                LocalDate.parse(customerRequest.dateOfBirth()),
                customerRequest.email(),
                customerRequest.phoneNumber()
        );

        return new Cotacao(
                null,
                produtoResponse.id(),
                "",
                ofertaResponse.id(),
                insuranceRequest.category(),
                insuranceRequest.totalMonthlyPremiumAmount(),
                insuranceRequest.totalCoverageAmount(),
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                insuranceRequest.assistances(),
                ofertaResponse.coverages(),
                customer

        );
    }
}
