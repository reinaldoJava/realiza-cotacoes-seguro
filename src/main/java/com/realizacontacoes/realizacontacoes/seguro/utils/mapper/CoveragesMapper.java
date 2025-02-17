package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;


import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database.entity.CoveragesEntity;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CoveragesMapper {

    default List<CoveragesEntity> map(Map<String, Double> coverages) {
        if (coverages == null) {
            return List.of();
        }
        return coverages.entrySet().stream()
                .map(entry -> {
                    CoveragesEntity coverageEntity = new CoveragesEntity();
                    coverageEntity.setType(entry.getKey());
                    coverageEntity.setAmount(BigDecimal.valueOf(entry.getValue()));
                    return coverageEntity;
                })
                .collect(Collectors.toList());
    }

    default Map<String, Double> map(List<CoveragesEntity> coverages) {
        if (coverages == null) {
            return Map.of();
        }
        return coverages.stream()
                .collect(Collectors.toMap(
                        CoveragesEntity::getType,
                        coveragesEntity -> coveragesEntity.getAmount().doubleValue()
                ));
    }
}