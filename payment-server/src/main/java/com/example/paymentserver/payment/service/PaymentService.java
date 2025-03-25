package com.example.paymentserver.payment.service;

import com.example.paymentserver.payment.client.TossWebClient;
import com.example.paymentserver.payment.mapper.PaymentMapper;
import com.example.paymentserver.payment.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final TossWebClient webClient;
    private final BillingRepository billingRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(TossWebClient webClient, BillingRepository billingRepository, PaymentMapper paymentMapper) {
        this.webClient = webClient;
        this.billingRepository = billingRepository;
        this.paymentMapper = paymentMapper;
    }

    public void saveBillingKey() {

    }

    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}
