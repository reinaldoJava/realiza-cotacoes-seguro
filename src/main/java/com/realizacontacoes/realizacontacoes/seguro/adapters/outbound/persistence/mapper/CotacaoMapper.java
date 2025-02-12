package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.mapper;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity.CoveragesEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity.CustomerEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.*;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.CustomerRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.OfertaResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.ProdutoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CotacaoMapper {

    public Cotacao toDomain(CotacaoEntity entity) {
        return new Cotacao(
                entity.getId(),
                entity.getProductId(),
                entity.getOfferId(),
                entity.getCategory(),
                entity.getTotalMonthlyPremiumAmount(),
                entity.getTotalCoverageAmount(),
                entity.getCoverages().stream()
                        .map(this::toCoverage)
                        .collect(Collectors.toList()),
                entity.getAssistances(),
                toCustomer(entity.getCustomer())
        );
    }

    public CotacaoEntity toEntity(Cotacao record) {
        CotacaoEntity entity = new CotacaoEntity();
        entity.setId(record.id());
        entity.setProductId(record.productId());
        entity.setOfferId(record.offerId());
        entity.setCategory(record.category());
        entity.setTotalMonthlyPremiumAmount(record.totalMonthlyPremiumAmount());
        entity.setTotalCoverageAmount(record.totalCoverageAmount());
        entity.setCoverages(record.coverages().stream()
                .map(this::toCoverageEntity)
                .collect(Collectors.toList()));
        entity.setAssistances(record.assistances());
        entity.setCustomer(toCustomerEntity(record.customer()));
        return entity;
    }

    private Coverage toCoverage(CoveragesEntity entity) {
        return new Coverage(
                entity.getId(),
                entity.getType(),
                entity.getAmount(),
                entity.getCotacao().getId()
        );
    }

    private CoveragesEntity toCoverageEntity(Coverage record) {
        CoveragesEntity entity = new CoveragesEntity();
        entity.setId(record.id());
        entity.setType(record.type());
        entity.setAmount(record.amount());
        // Você precisará buscar a CotacaoEntity correspondente ao ID
        // entity.setCotacao(cotacaoRepository.findById(record.cotacaoId()).orElseThrow());
        return entity;
    }

    private Customer toCustomer(CustomerEntity entity) {
        return new Customer(
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

    private CustomerEntity toCustomerEntity(Customer record) {
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
    public static Cotacao criarCotacao(ProdutoResponse produtoResponse, OfertaResponse ofertaResponse, InsuranceRequest insuranceRequest) {
        // Mapeando os subobjetos Coverage
        List<Coverage> coverages = ofertaResponse.coverages().entrySet().stream()
                .map(entry -> new Coverage(null, entry.getKey(), entry.getValue(), null))
                .collect(Collectors.toList());

        // Criando o objeto Customer
        CustomerRequest customerRequest = insuranceRequest.customerRequest();
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

        // Criando e retornando o objeto Cotacao
        return new Cotacao(
                null,
                produtoResponse.id(),
                ofertaResponse.id(),
                insuranceRequest.category(),
                insuranceRequest.totalMonthlyPremiumAmount(),
                insuranceRequest.totalCoverageAmount(),
                coverages,
                insuranceRequest.assistances(),
                customer
        );
    }
}