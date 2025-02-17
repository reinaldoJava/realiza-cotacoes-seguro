package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.avro.CotacaoAvro;
import com.realizacontacoes.realizacontacoes.seguro.usecase.CotacaoConsumerUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class CotacaoKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoKafkaConsumer.class);
    private final CotacaoConsumerUseCase cotacaoConsumerUseCase;
    private CountDownLatch latch = new CountDownLatch(1);
    private String receivedMessage;

    public CotacaoKafkaConsumer(CotacaoConsumerUseCase cotacaoConsumerUseCase) {
        this.cotacaoConsumerUseCase = cotacaoConsumerUseCase;
    }

    //TODO Rever se esses campos sao necessarios.
    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, CotacaoAvro> response) {
        LOGGER.info("received payload='{}'", response.value());
        cotacaoConsumerUseCase.processarCotacao(response.value());
        latch.countDown();
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
