package com.example.paymentserver.global.kafka;

import com.example.paymentserver.payment.dto.event.SubscriptionFailedEvent;
import com.example.paymentserver.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer {
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    public PaymentKafkaConsumer(ObjectMapper objectMapper, PaymentService paymentService) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "subscription-failed", groupId = "payment-group")
    public void consumeSubscriptionFailed(ConsumerRecord<String, String> record) throws JsonProcessingException {
        SubscriptionFailedEvent event = objectMapper.readValue(record.value(), SubscriptionFailedEvent.class);
        paymentService.refundPaymentByPaymentId(event.getPaymentId());
    }
}
