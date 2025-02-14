package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;

import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;
import com.realizacontacoes.realizacontacoes.avro.Customer;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity.CoveragesEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity.CustomerEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CoverageDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.CustomerRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.response.ProdutoResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
//TODO Refatorar para MapStruct
public class CotacaoMapper {


    public static CotacaoDTO toDomain(CotacaoEntity entity) {
        if (entity == null) {
            return null; // Retorna null se a entidade for nula para evitar NullPointerException
        }

        // Evita NullPointerException caso coverages seja nulo
        List<CoverageDTO> coverageDTOS = new ArrayList<>();
        for (CoveragesEntity coveragesEntity : Optional.ofNullable(entity.getCoverages())
                .orElse(Collections.emptyList())) {
            CoverageDTO coverage = toCoverage(coveragesEntity);
            coverageDTOS.add(coverage);
        }

        return new CotacaoDTO(
                entity.getId(),
                entity.getProductId(),
                entity.getOfferId(),
                entity.getCategory(),
                entity.getTotalMonthlyPremiumAmount(),
                entity.getTotalCoverageAmount(),
                coverageDTOS,
                Optional.ofNullable(entity.getAssistances()).orElse(Collections.emptyList()),
                toCustomer(entity.getCustomer()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()//
        );
    }

    public static CotacaoEntity toEntity(CotacaoDTO record) {
        CotacaoEntity entity = new CotacaoEntity();
        entity.setId(record.id());
        entity.setProductId(record.productId());
        entity.setOfferId(record.offerId());
        entity.setCategory(record.category());
        entity.setTotalMonthlyPremiumAmount(record.totalMonthlyPremiumAmount());
        entity.setTotalCoverageAmount(record.totalCoverageAmount());

        List<CoveragesEntity> coveragesEntities = record.coverageDTOS().stream()
                .map(cov -> toCoverageEntity(cov, entity))
                .collect(Collectors.toList());
        entity.setCoverages(coveragesEntities);

        entity.setAssistances(record.assistances());
        entity.setCustomer(toCustomerEntity(record.customerDTO()));

        return entity;
    }

    private static CoveragesEntity toCoverageEntity(CoverageDTO record, CotacaoEntity cotacaoEntity) {
        CoveragesEntity entity = new CoveragesEntity();
        entity.setType(record.type());
        entity.setAmount(record.amount());
        entity.setCotacao(cotacaoEntity);  // ✅ Associando corretamente a cotação
        return entity;
    }

    private static CoverageDTO toCoverage(CoveragesEntity entity) {
        return new CoverageDTO(
                entity.getId(),
                entity.getType(),
                entity.getAmount(),
                entity.getCotacao().getId()
        );
    }

    private static CustomerDTO toCustomer(CustomerEntity entity) {
        return new CustomerDTO(
                entity.getId(),
                entity.getDocumentNumber(),
                entity.getName(),
                entity.getType(),
                entity.getGender(),
                entity.getDateOfBirth(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }

    private static CustomerEntity toCustomerEntity(CustomerDTO record) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(record.id());
        entity.setDocumentNumber(record.documentNumber());
        entity.setName(record.name());
        entity.setType(record.type());
        entity.setGender(record.gender());
        entity.setDateOfBirth(record.dateOfBirth());
        entity.setEmail(record.email());
        entity.setPhoneNumber(record.phoneNumber());
        return entity;
    }
    public static CotacaoDTO criarCotacao(ProdutoResponse produtoResponse, OfertaResponse ofertaResponse, InsuranceRequest insuranceRequest) {
        // Mapeando os subobjetos Coverage
        List<CoverageDTO> coverageDTOS = ofertaResponse.coverages().entrySet().stream()
                .map(entry -> new CoverageDTO(null, entry.getKey(), entry.getValue(), null))
                .collect(Collectors.toList());

        // Criando o objeto Customer
        CustomerRequest customerRequest = insuranceRequest.customer();
        CustomerDTO customerDTO = new CustomerDTO(
                null,
                customerRequest.documentNumber(),
                customerRequest.name(),
                customerRequest.type(),
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
                coverageDTOS,
                insuranceRequest.assistances(),
                customerDTO,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
    public static CotacaoDTO fromAvro(CotacaoResponse avro) {
        return new CotacaoDTO(
                avro.getId(),
                avro.getProductId().toString(),
                avro.getOfferId().toString(),
                avro.getCategory().toString(),
                BigDecimal.valueOf(avro.getTotalMonthlyPremiumAmount()),
                BigDecimal.valueOf(avro.getTotalCoverageAmount()),
                convertCoverages(avro.getCoverages(), avro.getId()),
                avro.getAssistances().stream().map(CharSequence::toString).toList(),
                convertCustomer(avro.getCustomer(), avro.getId()),
                LocalDateTime.parse(avro.getCreatedAt(),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")),
                LocalDateTime.parse(avro.getUpdatedAt(),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
        );
    }

    private static List<CoverageDTO> convertCoverages(Map<CharSequence, Double> coverages, Long cotacaoId) {
        return coverages.entrySet().stream()
                .map(entry -> new CoverageDTO(
                        null,  // ID pode ser gerado no banco de dados
                        entry.getKey().toString(),
                        BigDecimal.valueOf(entry.getValue()),
                        cotacaoId
                ))
                .collect(Collectors.toList());
    }

    private static CustomerDTO convertCustomer(Customer avro, Long cotacaoId) {
        return new CustomerDTO(
                null,  // ID pode ser gerado no banco de dados
                avro.getDocumentNumber().toString(),
                avro.getName().toString(),
                avro.getType().toString(),
                avro.getGender().toString(),
                Instant.parse(avro.getDateOfBirth().toString()).atZone(ZoneOffset.UTC).toLocalDate(),
                avro.getEmail().toString(),
                avro.getPhoneNumber()
        );
    }

}