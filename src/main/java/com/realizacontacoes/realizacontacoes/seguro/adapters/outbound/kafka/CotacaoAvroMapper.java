package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;


import com.realizacontacoes.realizacontacoes.avro.CotacaoRequest;
import com.realizacontacoes.realizacontacoes.avro.Customer;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CoverageDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CotacaoAvroMapper {

    public static CotacaoRequest toAvro(CotacaoDTO dto) {
        List<CharSequence> listaDeCharSequence = new ArrayList<>(dto.assistances());
        return CotacaoRequest.newBuilder()
                .setId(dto.id())
                .setProductId(dto.productId())
                .setOfferId(dto.offerId())
                .setCategory(dto.category())
                .setCreatedAt(dto.createdAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")))
                .setUpdatedAt(dto.updatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")))
                .setTotalMonthlyPremiumAmount(dto.totalMonthlyPremiumAmount().doubleValue())
                .setTotalCoverageAmount(dto.totalCoverageAmount().doubleValue())
                .setCoverages(toAvroCoverages(dto.coverageDTOS()))
                .setAssistances(listaDeCharSequence)
                .setCustomer(toAvroCustomer(dto.customerDTO()))
                .build();
    }

    private static Map<CharSequence, Double> toAvroCoverages(List<CoverageDTO> coverages) {
        return coverages.stream()
                .collect(Collectors.toMap(CoverageDTO::type, coverage -> coverage.amount().doubleValue()));
    }

    private static Customer toAvroCustomer(CustomerDTO customer) {
        return Customer.newBuilder()
                .setDocumentNumber(customer.documentNumber())
                .setName(customer.name())
                .setType(customer.type())
                .setGender(customer.gender())
                .setDateOfBirth(customer.dateOfBirth().toString())
                .setEmail(customer.email())
                .setPhoneNumber(customer.phoneNumber())
                .build();
    }
}
