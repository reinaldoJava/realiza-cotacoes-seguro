package com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.rest;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.request.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.input.ProcessaCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.CotacaoMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CotacaoController.class)
public class CotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProcessaCotacaoPort processaCotacaoPort;

    private final String BASE_URL = "/cotacao";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCotacao() throws Exception {

        doNothing().when(processaCotacaoPort).processarCotacao(any(InsuranceRequest.class));
        String jsonRequest = "{\"product_id\":\"1b2da7cc-b367-4196-8a78-9cfeec21f587\",\"offer_id\":\"adc56d77-348c-4bf0-908f-22d402ee715c\",\"category\":\"HOME\",\"total_monthly_premium_amount\":75.25,\"total_coverage_amount\":825000.00,\"coverages\":{\"Incêndio\":250000.00,\"Desastres naturais\":500000.00,\"Responsabiliadade civil\":75000.00},\"assistances\":[\"Encanador\",\"Eletricista\",\"Chaveiro 24h\"],\"customer\":{\"document_number\":\"36205578900\",\"name\":\"John Wick\",\"type\":\"NATURAL\",\"gender\":\"MALE\",\"date_of_birth\":\"1973-05-02\",\"email\":\"johnwick@gmail.com\",\"phone_number\":11950503030}}";

        mockMvc.perform(post(BASE_URL + "/solicitar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("Cotação recebida com sucesso."));
    }

    @Test
    public void testBuscarCotacaoPorId() throws Exception {
        when(processaCotacaoPort.buscarCotacaoPorId(22345L)).thenReturn(CotacaoMock.createMockCotacao());

        mockMvc.perform(get(BASE_URL + "/22345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22345L));
    }

    @Test
    public void testBuscarCotacaoPorIdNotFound() throws Exception {
        when(processaCotacaoPort.buscarCotacaoPorId(99999L)).thenThrow(new RuntimeException("Cotação não encontrada com o ID: 99999"));

        mockMvc.perform(get(BASE_URL + "/99999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cotação não encontrada com o ID: 99999"));
    }

    @Test
    public void testSolicitarCotacaoInvalida() throws Exception {
        mockMvc.perform(post(BASE_URL + "/solicitar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Requisição inválida")));
    }
}

