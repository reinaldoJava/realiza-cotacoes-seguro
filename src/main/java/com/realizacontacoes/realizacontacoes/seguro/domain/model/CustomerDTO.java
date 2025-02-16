package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.time.LocalDate;
import java.util.Optional;

public record CustomerDTO(Optional<Long> id,
                          String documentNumber,
                          String name,
                          String type,
                          String gender,
                          LocalDate dateOfBirth,
                          String email,
                          Long phoneNumber) {
}
