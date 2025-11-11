package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.time.LocalDate;
import java.util.Optional;
//TODO verificar se o ID esta funcionando.
public record Customer(Optional<Long> id,
                       String documentNumber,
                       String name,
                       String type,
                       String gender,
                       LocalDate dateOfBirth,
                       String email,
                       Long phoneNumber) {
}
