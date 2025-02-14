package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;


import com.realizacontacoes.realizacontacoes.avro.CotacaoRequest;
import com.realizacontacoes.realizacontacoes.avro.Customer;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CoverageDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;

import java.time.ZoneOffset;
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
                .setCreatedAt(dto.createdAt().toInstant(ZoneOffset.UTC).toString())
                .setUpdatedAt(dto.updatedAt().toInstant(ZoneOffset.UTC).toString())
                .setTotalMonthlyPremiumAmount(dto.totalMonthlyPremiumAmount().doubleValue())
                .setTotalCoverageAmount(dto.totalCoverageAmount().doubleValue())
                .setCoverages(toAvroCoverages(dto.coveragesDTO()))
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
                .setType(customer.customerType())
                .setGender(customer.gender())
                .setDateOfBirth(customer.dateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .setEmail(customer.email())
                .setPhoneNumber(customer.phoneNumber().toString())
                .build();
    }
}
