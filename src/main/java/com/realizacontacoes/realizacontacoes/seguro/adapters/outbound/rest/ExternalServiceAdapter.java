package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;


import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProductResponse;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.ExternalServicePort;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceAdapter implements ExternalServicePort {

    private final ExternalUserClient externalUserClient;

    public ExternalServiceAdapter(ExternalUserClient externalUserClient) {
        this.externalUserClient = externalUserClient;
    }

    @Override
    public ProductResponse getExternalData(String id) {
        return externalUserClient.getExternalData(id);
    }
}
