package com.realizacontacoes.realizacontacoes.seguro.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

record Customer(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String documentNumber,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String name,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String type,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String gender,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String dateOfBirth,
        @Email String email,
        @NotNull Long phoneNumber
) {}