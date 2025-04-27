package com.example.tranformers;

import java.util.List;

public interface KafkaTransform {
    String getTopicName();
    List<String> getKey();
    String getNameSpace();
}
