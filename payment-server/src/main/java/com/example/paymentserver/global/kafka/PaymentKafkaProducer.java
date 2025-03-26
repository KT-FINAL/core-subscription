package com.example.paymentserver.global.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentCompletedEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
