package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import java.time.LocalDate;

public record Customer(Long id,
                       String documentNumber,
                       String name,
                       String type,
                       String gender,
                       LocalDate dateOfBirth,
                       String email,
                       Long phoneNumber) {
}
