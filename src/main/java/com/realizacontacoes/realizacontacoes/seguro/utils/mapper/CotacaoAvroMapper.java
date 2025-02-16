package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;


import com.realizacontacoes.realizacontacoes.avro.CotacaoAvro;
import com.realizacontacoes.realizacontacoes.avro.CotacaoAvro;
import com.realizacontacoes.realizacontacoes.avro.Customer;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CotacaoAvroMapper {

    public static CotacaoAvro toAvro(CotacaoDTO dto) {
        return CotacaoAvro.newBuilder()
                .setId(dto.id())
                .setProductId(dto.productId())
                .setOfferId(dto.offerId())
                .setCategory(dto.category())
                .setCreatedAt(dto.createdAt())
                .setUpdatedAt(dto.updatedAt())
                .setTotalMonthlyPremiumAmount(dto.totalMonthlyPremiumAmount().doubleValue())
                .setTotalCoverageAmount(dto.totalCoverageAmount().doubleValue())
                .setCoverages(convertCoverages(dto.coverages()))
                .setAssistances(convertAssistances(dto.assistances()))
                .setCustomer(convertCustomer(dto.customerDTO()))
                .build();
    }

    public static CotacaoDTO toRequestDTO(CotacaoAvro request) {
        return new CotacaoDTO(
                request.getId(),
                request.getProductId().toString(),
                Optional.empty(),
                request.getOfferId().toString(),
                request.getCategory().toString(),
                BigDecimal.valueOf(request.getTotalMonthlyPremiumAmount()),
                BigDecimal.valueOf(request.getTotalCoverageAmount()),
                request.getCreatedAt().toString(),
                request.getUpdatedAt().toString(),
                convertAssistancesToList(request.getAssistances()),
                convertCoveragesToMap(request.getCoverages()),
                convertCustomerToDTO(request.getCustomer())
        );
    }
    public static CotacaoDTO toResponseDTO(CotacaoAvro response) {
        return new CotacaoDTO(
                response.getId(),
                response.getProductId().toString(),
                Optional.of(response.getInsurancePolicyId().toString()),
                response.getOfferId().toString(),
                response.getCategory().toString(),
                BigDecimal.valueOf(response.getTotalMonthlyPremiumAmount()),
                BigDecimal.valueOf(response.getTotalCoverageAmount()),
                response.getCreatedAt().toString(),
                response.getUpdatedAt().toString(),
                convertAssistancesToList(response.getAssistances()),
                convertCoveragesToMap(response.getCoverages()),
                convertCustomerToDTO(response.getCustomer())
        );
    }

    private static Customer convertCustomer(CustomerDTO customerDTO) {
        return Customer.newBuilder()
                .setDocumentNumber(customerDTO.documentNumber())
                .setName(customerDTO.name())
                .setType(customerDTO.type())
                .setGender(customerDTO.gender())
                .setDateOfBirth(customerDTO.dateOfBirth().toString())
                .setEmail(customerDTO.email())
                .setPhoneNumber(customerDTO.phoneNumber())
                .build();
    }

    private static CustomerDTO convertCustomerToDTO(Customer customer) {
        return new CustomerDTO(
                Optional.empty(),//ID vazio
                customer.getDocumentNumber().toString(),
                customer.getName().toString(),
                customer.getType().toString(),
                customer.getGender().toString(),
                LocalDate.parse(customer.getDateOfBirth()),
                customer.getEmail().toString(),
                customer.getPhoneNumber()
        );
    }
    private static Map<CharSequence, Double> convertCoverages(Map<String, Double> coverages) {
        return coverages.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map<String, Double> convertCoveragesToMap(Map<CharSequence, Double> coverages) {
        return coverages.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), Map.Entry::getValue));
    }

    private static List<CharSequence> convertAssistances(List<String> assistances) {
        return assistances.stream()
                .map(String::new)
                .collect(Collectors.toList());
    }

    private static List<String> convertAssistancesToList(List<CharSequence> assistances) {
        return assistances.stream()
                .map(CharSequence::toString)
                .collect(Collectors.toList());
    }
}
