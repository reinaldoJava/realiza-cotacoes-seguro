package com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.InsuranceRequestMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CotacaoController.class)
class CotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProcessaCotacaoPort processaCotacaoPort;

    private static final String URL = "/cotacao/solicitar";

    @Test
    void deveRetornar200QuandoRequisicaoValida() throws Exception {
        InsuranceRequest validRequest = InsuranceRequestMock.createValidRequest();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cotação recebida com sucesso."));

        verify(processaCotacaoPort, times(1)).processarCotacao(any(InsuranceRequest.class));
    }

    @Test
    void deveRetornar400QuandoRequisicaoInvalida() throws Exception {
        InsuranceRequest invalidRequest = InsuranceRequestMock.createInvalidRequest();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invalidRequest)))
                .andExpect(status().isBadRequest());

        verify(processaCotacaoPort, never()).processarCotacao(any(InsuranceRequest.class));
    }

    @Test
    void deveRetornar500QuandoProcessamentoFalhar() throws Exception {
        InsuranceRequest validRequest = InsuranceRequestMock.createValidRequest();

        doThrow(new RuntimeException("Erro interno")).when(processaCotacaoPort).processarCotacao(any(InsuranceRequest.class));

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validRequest)))
                .andExpect(status().isInternalServerError());

        verify(processaCotacaoPort, times(1)).processarCotacao(any(InsuranceRequest.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
