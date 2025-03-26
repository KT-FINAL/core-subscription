package com.example.subscriptionserver.global.kafka;

import com.example.subscriptionserver.domain.subscription.dto.event.PaymentCompleteEvent;
import com.example.subscriptionserver.domain.subscription.dto.event.SubscriptionFailedEvent;
import com.example.subscriptionserver.domain.subscription.service.SubscriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionKafkaConsumer {
    private final SubscriptionService subscriptionService;
    private final ObjectMapper objectMapper;

    public SubscriptionKafkaConsumer(SubscriptionService subscriptionService, ObjectMapper objectMapper) {
        this.subscriptionService = subscriptionService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "payment-completed", groupId = "subscription-group")
    public void consumePaymentComleted(ConsumerRecord<String, String> record) throws JsonProcessingException {
        PaymentCompleteEvent event = objectMapper.readValue(record.value(), PaymentCompleteEvent.class);

        subscriptionService.registerSubscription(event);
    }
}
