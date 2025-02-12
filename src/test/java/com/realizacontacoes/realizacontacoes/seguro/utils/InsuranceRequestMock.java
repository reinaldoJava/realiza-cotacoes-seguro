package com.realizacontacoes.realizacontacoes.seguro.utils;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

public class InsuranceRequestMock {

    public static InsuranceRequest createValidRequest() {
        return Instancio.of(validRequestModel()).create();
    }

    public static InsuranceRequest createInvalidRequest() {
        return Instancio.of(InsuranceRequest.class)
                .ignore(field("productId"))
                .ignore(field("offerId"))
                .ignore(field("category"))
                .ignore(field("customer"))
                .create();
    }

    private static Model<InsuranceRequest> validRequestModel() {
        return Instancio.of(InsuranceRequest.class)
                .set(field("category"), "HOME")
                .toModel();
    }
}