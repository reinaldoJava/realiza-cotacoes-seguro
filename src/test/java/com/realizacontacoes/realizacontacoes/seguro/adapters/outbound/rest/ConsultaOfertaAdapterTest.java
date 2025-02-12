package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.rest;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.wiremock.spring.EnableWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@EnableWireMock
//@ActiveProfiles(value = "test")
@ImportAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@WireMockTest(httpPort = 9091)
@TestPropertySource(locations="classpath:application-test.properties")
//TODO Rever o motivo do wiremock  nao esta funcionando
class ConsultaOfertaAdapterTest {

    private static final int PORT = 9091;

    private ConsultaOfertaAdapter consultaOfertaAdapter;

    @MockitoBean
    private ConsultaOfertaServiceExternal consultaProdutoServiceExternal;
    @BeforeEach
    void setUp() {
        consultaOfertaAdapter = new ConsultaOfertaAdapter(consultaProdutoServiceExternal);
        WireMock.configureFor("localhost", PORT);
    }

    @Test
    @DisplayName("Deve retornar uma oferta válida ao buscar por ID")
    void deveRetornarOfertaValida() {
        stubFor(get(urlEqualTo("/servicos-externos/consulta-oferta/adc56d77-348c-4bf0-908f-22d402ee715c"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "id": "adc56d77-348c-4bf0-908f-22d402ee715c",
                                "product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
                                "name": "Seguro de Vida Familiar",
                                "created_at": "2021-07-01T00:00:00Z",
                                "active": true
                            }
                        """)));

        //OfertaResponse oferta = consultaOfertaAdapter.getOfertaById("adc56d77-348c-4bf0-908f-22d402ee715c");

        assertNotNull(consultaOfertaAdapter.getOfertaById("adc56d77-348c-4bf0-908f-22d402ee715c"));
    }

    @Test
    @DisplayName("Deve retornar erro 404 quando a oferta não for encontrada")
    void deveRetornarErroQuandoOfertaNaoEncontrada() {
        stubFor(get(urlEqualTo("/consulta-oferta/nao-existe"))
                .willReturn(aResponse().withStatus(404)));

        Assertions.assertThrows(Exception.class, () -> {
            consultaOfertaAdapter.getOfertaById("nao-existe");
        });
    }

    @Test
    @DisplayName("Deve retornar erro 500 quando o serviço externo falhar")
    void deveRetornarErroQuandoServicoFalhar() {
        stubFor(get(urlEqualTo("/consulta-oferta/erro-servidor"))
                .willReturn(aResponse().withStatus(500)));

        Assertions.assertThrows(Exception.class, () -> {
            consultaOfertaAdapter.getOfertaById("erro-servidor");
        });
    }
}