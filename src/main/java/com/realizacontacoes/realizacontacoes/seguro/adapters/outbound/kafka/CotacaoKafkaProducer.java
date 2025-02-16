package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.avro.CotacaoAvro;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput.EnviaMensagemCotacaoPort;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoAvroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CotacaoKafkaProducer implements EnviaMensagemCotacaoPort {

    private final KafkaTemplate<String, CotacaoAvro> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CotacaoKafkaProducer.class);

    public CotacaoKafkaProducer(KafkaTemplate<String, CotacaoAvro> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void enviarCotacao(String topic, CotacaoDTO cotacaoDTO) {
        CotacaoAvro avroCotacao = CotacaoAvroMapper.toAvro(cotacaoDTO);
        CompletableFuture<SendResult<String, CotacaoAvro>> future = kafkaTemplate.send(topic, avroCotacao);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Mensagem enviada para Kafka: " + result.getProducerRecord().value());
            } else {
                LOGGER.error("Erro ao enviar mensagem para Kafka: " + ex.getMessage());
            }
        });
    }
}
