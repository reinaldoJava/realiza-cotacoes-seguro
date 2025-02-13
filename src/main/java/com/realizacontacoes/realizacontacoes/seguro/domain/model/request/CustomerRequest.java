package com.realizacontacoes.realizacontacoes.seguro.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @JsonProperty("document_number")
        String documentNumber,
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_BLANK) String name,
        String type,
        String gender,
        @JsonProperty("date_of_birth")
        String dateOfBirth,
        @Email String email,
        @JsonProperty("phone_number")
        @NotNull Long phoneNumber
) {}