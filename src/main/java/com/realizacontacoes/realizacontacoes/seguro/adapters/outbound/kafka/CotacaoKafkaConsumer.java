package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.seguro.usecase.service.CotacaoConsumerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class CotacaoKafkaConsumer {

    private String receivedMessage;
    private final CotacaoConsumerUseCase cotacaoConsumerUseCase;
    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoKafkaConsumer.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public CotacaoKafkaConsumer(CotacaoConsumerUseCase cotacaoConsumerUseCase) {
        this.cotacaoConsumerUseCase = cotacaoConsumerUseCase;
    }

    @KafkaListener(topics = "topic")
    public void receiveMessage(String message) {
        this.receivedMessage = message;
        LOGGER.info("received payload='{}'", message);
        cotacaoConsumerUseCase.processarCotacao(message);
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