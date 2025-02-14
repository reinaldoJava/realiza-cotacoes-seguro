package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.avro.CotacaoRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ContacaoKafkaProducer  implements EnviaMensagemCotacaoPort {

    private final KafkaTemplate<String, CotacaoRequest> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContacaoKafkaProducer.class);

    public ContacaoKafkaProducer(KafkaTemplate<String, CotacaoRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void enviarCotacao(String topic, CotacaoDTO cotacaoDTO) {

        CompletableFuture<SendResult<String, CotacaoRequest>> future =
                kafkaTemplate.send(topic, CotacaoAvroMapper.toAvro(cotacaoDTO));

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Mensagem enviada para Kafka: s" + result.getProducerRecord().value());
            } else {
                LOGGER.error("Erro ao enviar mensagem para Kafka: " + ex.getMessage());
            }
        });
    }
}
