package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;

import com.realizacontacoes.realizacontacoes.avro.CotacaoRequest;
import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CotacaoEntity;
import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CoveragesEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CoveragesMapper.class})
public interface CotacaoMapper {

    // Mapeamento de CotacaoDTO para CotacaoEntity
    @Mapping(source = "customerDTO", target = "customer")
    @Mapping(source = "coverages", target = "coverages", qualifiedByName = "mapCoverages")
    @Mapping(source = "assistances", target = "assistances", qualifiedByName = "mapAssistances")
    CotacaoEntity toEntity(CotacaoDTO dto);

    // Mapeamento de CotacaoEntity para CotacaoDTO
    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(source = "coverages", target = "coverages", qualifiedByName = "mapCoverages")
    @Mapping(source = "assistances", target = "assistances", qualifiedByName = "mapAssistances")
    CotacaoDTO toDomain(CotacaoEntity entity);

    // Método de mapeamento para Coverages (Map para List<CoveragesEntity>)
    @Named("mapCoverages")
    static List<CoveragesEntity> mapCoverages(Map<CharSequence, Double> coverages) {
        if (coverages == null) {
            return Collections.emptyList();
        }
        return coverages.entrySet().stream()
                .map(entry -> {
                    CoveragesEntity coverage = new CoveragesEntity();
                    coverage.setType(entry.getKey().toString());  // Conversão de CharSequence para String
                    coverage.setAmount(BigDecimal.valueOf(entry.getValue()));
                    return coverage;
                })
                .collect(Collectors.toList());
    }

    // Método de mapeamento para Assistances (List<CharSequence> para List<String>)
    @Named("mapAssistances")
    static List<String> mapAssistances(List<CharSequence> assistances) {
        if (assistances == null) {
            return Collections.emptyList();
        }
        return assistances.stream()
                .map(CharSequence::toString)
                .collect(Collectors.toList());
    }
}