package com.example.api.config;

import com.example.Customer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.io.ByteArrayOutputStream;
import java.util.function.Consumer;

@Configuration
public class KafkaConfig {
    public Customer convertMessageToAvroObject(GenericData.Record genericRecord) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(baos, null);
            GenericDatumWriter<GenericData.Record> writer = new GenericDatumWriter<>(genericRecord.getSchema());
            writer.write(genericRecord, encoder);
            encoder.flush();

            byte[] bytes = baos.toByteArray();
            BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
            SpecificDatumReader<Customer> reader = new SpecificDatumReader<>(Customer.getClassSchema());
            //
            return reader.read(null, decoder);
        } catch (Exception e) {
            throw new RuntimeException("Error converting message to Avro object", e);
        }
    }


    @Bean(name = "input")
    public Consumer<Customer> input() {
        return (s) -> {
            System.out.println("received message: " + s);
        };
    }

    public Consumer<Message<GenericData.Record>> genericInput() {
        return (s) -> {
            GenericData.Record record = s.getPayload();
            String topic = s.getHeaders().get("kafka_receivedTopic", String.class);
            String key = s.getHeaders().get("kafka_receivedMessageKey", String.class);
            Customer customer = convertMessageToAvroObject(record);
            System.out.println("Received message: " + topic + " " + record + " " + key);
            System.out.println(customer);
        };
    }

}
