package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import com.realizacontacoes.realizacontacoes.avro.CotacaoResponse;
import com.realizacontacoes.realizacontacoes.avro.Customer;
import com.realizacontacoes.realizacontacoes.seguro.usecase.service.CotacaoConsumerUseCase;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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

    @KafkaListener(topics = "topic-external", groupId = "acme")
    public void consume(@Payload GenericRecord record){
        this.receivedMessage = record.toString();
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
    private CotacaoResponse parseAvro(GenericRecord record) {
        return CotacaoResponse.newBuilder()
                .setId((Long) record.get("id"))
                .setInsurancePolicyId((Long) record.get("insurance_policy_id"))
                .setProductId(record.get("product_id").toString())
                .setOfferId(record.get("offer_id").toString())
                .setCategory(record.get("category").toString())
                .setCreatedAt(record.get("created_at").toString())
                .setUpdatedAt(record.get("updated_at").toString())
                .setTotalMonthlyPremiumAmount((Double) record.get("total_monthly_premium_amount"))
                .setTotalCoverageAmount((Double) record.get("total_coverage_amount"))
                .setCoverages((Map<CharSequence, Double>) record.get("coverages"))
                .setAssistances((List<CharSequence>) record.get("assistances"))
                .setCustomer(parseCustomer((GenericRecord) record.get("customer")))
                .build();
    }

    private Customer parseCustomer(GenericRecord record) {
        return Customer.newBuilder()
                .setDocumentNumber(record.get("document_number").toString())
                .setName(record.get("name").toString())
                .setType(record.get("type").toString())
                .setGender(record.get("gender").toString())
                .setDateOfBirth(record.get("date_of_birth").toString())
                .setEmail(record.get("email").toString())
                .setPhoneNumber((Long) record.get("phone_number"))
                .build();
    }
}