package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String documentNumber,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String name,
        String type,
        String gender,
        String dateOfBirth,
        @Email String email,
        @NotNull Long phoneNumber
) {}