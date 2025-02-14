package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    private Class<T> targetType;

    public AvroDeserializer() {}

    public AvroDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        if (configs.containsKey("targetType")) {
            this.targetType = (Class<T>) configs.get("targetType");
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null || targetType == null) {
            return null;
        }

        try (ByteArrayInputStream in = new ByteArrayInputStream(data)) {
            BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(in, null);
            GenericDatumReader<T> reader = new GenericDatumReader<>(targetType.newInstance().getSchema());
            return reader.read(null, decoder);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar Avro", e);
        }
    }

    @Override
    public void close() {}
}