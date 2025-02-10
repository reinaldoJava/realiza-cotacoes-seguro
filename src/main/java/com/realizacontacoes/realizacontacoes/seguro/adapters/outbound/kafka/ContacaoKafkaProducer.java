package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContacaoKafkaProducer  implements EnviaMensagemCotacaoPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContacaoKafkaProducer.class);

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    public ContacaoKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(String payload) {
        LOGGER.info("enviando msg payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, payload);

    }
}
