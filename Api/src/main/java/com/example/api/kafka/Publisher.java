package com.example.api.kafka;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class Publisher {

    public void publish(String topic, Object message) {
        Message<Object> event = MessageBuilder.withPayload(message)
                .build();
    }
}
