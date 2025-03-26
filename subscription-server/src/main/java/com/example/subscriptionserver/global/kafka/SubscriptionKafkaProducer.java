package com.example.subscriptionserver.global.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SubscriptionKafkaProducer(
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, String event) {
        kafkaTemplate.send(topic, event);
    }
}
