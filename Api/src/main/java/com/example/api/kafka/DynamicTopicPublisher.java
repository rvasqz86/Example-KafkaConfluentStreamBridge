package com.example.api.kafka;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DynamicTopicPublisher {
    @Autowired
    private StreamBridge streamBridge;

    public void publish(String topicName, Object payload) {
        if(payload instanceof org.apache.avro.specific.SpecificRecordBase o){
            o.getSchema();
            GenericRecordBuilder g = new GenericRecordBuilder(o.getSchema());
            for(int i = 0; i < o.getSchema().getFields().size(); i++) {
                g.set(o.getSchema().getFields().get(i).name(), o.get(i));
            }
            Message<GenericData.Record> message = MessageBuilder.withPayload(g.build())
                    .build();
            streamBridge.send(topicName, message);
        }

    }
}