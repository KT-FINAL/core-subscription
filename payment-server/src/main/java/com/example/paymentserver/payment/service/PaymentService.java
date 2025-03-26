package com.example.paymentserver.payment.service;

import com.example.paymentserver.payment.client.TossWebClient;
import com.example.paymentserver.payment.dto.request.SaveBillingRequest;
import com.example.paymentserver.payment.dto.request.SavePaymentRequest;
import com.example.paymentserver.payment.dto.response.TossBillingKeyResponse;
import com.example.paymentserver.payment.dto.response.BillingResponse;
import com.example.paymentserver.payment.dto.response.PaymentEntityResponse;
import com.example.paymentserver.payment.dto.response.TossPaymentResponse;
import com.example.paymentserver.payment.entity.Billing;
import com.example.paymentserver.payment.entity.Payment;
import com.example.paymentserver.payment.mapper.PaymentMapper;
import com.example.paymentserver.payment.repository.BillingRepository;
import com.example.paymentserver.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PaymentService {
    private final TossWebClient webClient;
    private final BillingRepository billingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final TossWebClient tossWebClient;

    public PaymentService(
            TossWebClient webClient, BillingRepository billingRepository,
            PaymentRepository paymentRepository, PaymentMapper paymentMapper,
            TossWebClient tossWebClient
    ) {
        this.webClient = webClient;
        this.billingRepository = billingRepository;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.tossWebClient = tossWebClient;
    }

    @Transactional
    public BillingResponse saveBilling(SaveBillingRequest request) {
        TossBillingKeyResponse tossBillingKeyResponse
                = webClient.makeBillingKey(request.getCustomerKey(),request.getAuthKey()).block();

        Billing billing = billingRepository.save(
                paymentMapper.
                        billingKeyResponseToBilling(
                                request.getMemberId(), tossBillingKeyResponse)
        );

        return paymentMapper.billingToBillingResponse(billing);
    }

    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    private Billing getBillingByMemberId(Long memberId) {
        return billingRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public PaymentEntityResponse savePayment(SavePaymentRequest request) {
        Billing billing = getBillingByMemberId(request.getMemberId());
        String orderId = generateOrderId();

        TossPaymentResponse tossPaymentResponse = tossWebClient
                .paySubscription(billing.getBillingKey(),paymentMapper.toAutoPayRequest(billing, orderId)).block();

        Payment payment = paymentRepository.save(
                paymentMapper.toPaymentEntity(billing.getMemberId(), tossPaymentResponse)
        );

        return paymentMapper.toPaymentEntityResponse(payment);
    }

    @Transactional
    public void refundPayment() {

    }
}
