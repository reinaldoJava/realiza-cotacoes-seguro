package com.realizacontacoes.realizacontacoes.seguro.config;

import com.realizacontacoes.realizacontacoes.avro.CotacaoRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.producer.topic}")
    private String topic;
    private static final Integer PARTITION_COUNT = 1;
    private static final Integer REPLICA_COUNT = 1;

    @Bean
    public ProducerFactory<String, CotacaoRequest> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put("auto.register.schemas", true);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @Qualifier("TemplateTopic")
    public KafkaTemplate<String, CotacaoRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    @Bean
    public NewTopic buildTopic() {
        return TopicBuilder
                .name(topic)
                .replicas(REPLICA_COUNT)
                .partitions(PARTITION_COUNT)
                .build();
    }
}