package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.seguro.config.KafkaConfig;
import com.realizacontacoes.realizacontacoes.seguro.usecase.service.CotacaoConsumerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        bootstrapServersProperty = "spring.kafka.bootstrap-servers")
@ContextConfiguration(classes = {KafkaConfig.class})
@ImportAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class KafkaConsumerProducerTest {

    private ContacaoKafkaProducer kafkaProducer;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private String topic ="topic-external";

    private CotacaoKafkaConsumer cotacaoKafkaConsumer;
    @Mock
    private CotacaoConsumerUseCase cotacaoConsumerUseCase;

    @BeforeEach
    public void setup(){
        kafkaProducer = new ContacaoKafkaProducer(kafkaTemplate);
        cotacaoKafkaConsumer = new CotacaoKafkaConsumer(cotacaoConsumerUseCase);
    }

    @Test
    @DisplayName("Verificar se a mensagem foi enviada corretamente")
    public void testEnviarMensagem() throws Exception {
        String mensagem = "Olá, Kafka!";
        kafkaProducer.enviarMensagem(topic,mensagem);
        verify(kafkaTemplate, times(1)).send(topic, mensagem);
    }

    @Test
    @DisplayName("Verificar se a mensagem foi recebida corretamente")
    public void testReceberMensagem() throws Exception {
        String mensagem = "Olá, Kafka!";
        kafkaTemplate.send("topic", mensagem);
        cotacaoKafkaConsumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(cotacaoKafkaConsumer.getLatch().getCount() == 0);
        assertThat(cotacaoKafkaConsumer.getReceivedMessage(), equalTo(mensagem));
    }
}