package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.seguro.usecase.service.CotacaoConsumerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
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

    @KafkaListener(topics = "topic-external", groupId = "acme")
    public void consume(@Payload String record) {
        if (record == null || record.isEmpty()) {
            LOGGER.error("Payload value must not be empty");
            throw new IllegalArgumentException("Payload value must not be empty");
        }
        this.receivedMessage = record;
        LOGGER.info("received payload='{}'", this.receivedMessage);
        cotacaoConsumerUseCase.processarCotacao(this.receivedMessage);
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
