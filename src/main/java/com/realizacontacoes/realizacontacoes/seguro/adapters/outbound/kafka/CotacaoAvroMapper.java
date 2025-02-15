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
        Customer customer = Customer.newBuilder()
                .setDocumentNumber(dto.customerDTO().documentNumber())
                .setName(dto.customerDTO().name())
                .setType(dto.customerDTO().customerType())
                .setGender(dto.customerDTO().gender())
                .setDateOfBirth(dto.customerDTO().dateOfBirth().toString())
                .setEmail(dto.customerDTO().email())
                .setPhoneNumber(dto.customerDTO().phoneNumber())
                .build();

        // Conversão do map de coberturas
        Map<CharSequence, Double> coverages = dto.coverages().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        // Conversão da lista de assistências
        List<CharSequence> assistances = dto.assistances().stream()
                .map(String::new)
                .collect(Collectors.toList());

        // Criação da classe CotacaoRequest
        return CotacaoRequest.newBuilder()
                .setId(dto.id())
                .setProductId(dto.productId())
                .setOfferId(dto.offerId())
                .setCategory(dto.category())
                .setCreatedAt(dto.createdAt())
                .setUpdatedAt(dto.updatedAt())
                .setTotalMonthlyPremiumAmount(dto.totalMonthlyPremiumAmount().doubleValue())
                .setTotalCoverageAmount(dto.totalCoverageAmount().doubleValue())
                .setCoverages(coverages)
                .setAssistances(assistances)
                .setCustomer(customer)
                .build();
    }
}
