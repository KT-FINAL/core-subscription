package com.example.paymentserver.payment.mapper;

import com.example.paymentserver.payment.dto.request.TossAutoPayRequest;
import com.example.paymentserver.payment.dto.request.TossBillingKeyRequest;
import com.example.paymentserver.payment.dto.request.SaveBillingRequest;
import com.example.paymentserver.payment.dto.response.TossBillingKeyResponse;
import com.example.paymentserver.payment.dto.response.BillingResponse;
import com.example.paymentserver.payment.dto.response.PaymentEntityResponse;
import com.example.paymentserver.payment.dto.response.TossPaymentResponse;
import com.example.paymentserver.payment.entity.Billing;
import com.example.paymentserver.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Billing billingKeyResponseToBilling(Long memberId, TossBillingKeyResponse tossBillingKeyResponse) {
        return new Billing(
                memberId,
                tossBillingKeyResponse.getCustomerKey(),
                tossBillingKeyResponse.getBillingKey()
        );
    }

    public TossBillingKeyRequest saveBillingRequestToBillingKeyRequest(SaveBillingRequest saveBillingRequest) {
        return new TossBillingKeyRequest(
                saveBillingRequest.getCustomerKey(),
                saveBillingRequest.getAuthKey()
        );
    }

    public BillingResponse billingToBillingResponse(Billing billing) {
        return new BillingResponse(
                billing.getId(),
                billing.getMemberId(),
                billing.getCustomerKey(),
                billing.getBillingKey()
        );
    }

    public TossAutoPayRequest toAutoPayRequest(Billing billing, String orderId) {
        return new TossAutoPayRequest(
                9900,
                billing.getCustomerKey(),
                orderId,
                "[밀리의 서재+] 정기 구독"
        );
    }

    public Payment toPaymentEntity(Long memberId, TossPaymentResponse request) {
        return new Payment(
                memberId,
                request.getLastTransactionKey(),
                request.getPaymentKey(),
                request.getOrderId(),
                request.getOrderName(),
                request.getStatus(),
                request.getRequestedAt(),
                request.getApprovedAt(),
                request.getTotalAmount()
        );
    }

    public PaymentEntityResponse toPaymentEntityResponse(Payment payment) {
        return new PaymentEntityResponse (
                payment.getId(),
                payment.getMemberId(),
                payment.getLastTransactionKey(),
                payment.getPaymentKey(),
                payment.getOrderId(),
                payment.getOrderName(),
                payment.getStatus(),
                payment.getRequestedAt(),
                payment.getApprovedAt(),
                payment.getTotalAmount()
        );
    }
}
