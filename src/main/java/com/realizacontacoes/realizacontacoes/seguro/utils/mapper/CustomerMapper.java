package com.realizacontacoes.realizacontacoes.seguro.utils.mapper;

import com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.CustomerEntity;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toEntity(CustomerDTO dto);
    CustomerDTO toDomain(CustomerEntity entity);
}
