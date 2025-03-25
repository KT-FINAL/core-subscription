package com.example.paymentserver.payment.mapper;

import com.example.paymentserver.payment.dto.request.AutoPayRequest;
import com.example.paymentserver.payment.dto.request.BillingKeyRequest;
import com.example.paymentserver.payment.dto.request.SaveBillingRequest;
import com.example.paymentserver.payment.dto.response.BillingKeyResponse;
import com.example.paymentserver.payment.dto.response.BillingResponse;
import com.example.paymentserver.payment.dto.response.PaymentEntityResponse;
import com.example.paymentserver.payment.dto.response.PaymentResponse;
import com.example.paymentserver.payment.entity.Billing;
import com.example.paymentserver.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Billing billingKeyResponseToBilling(Long memberId, BillingKeyResponse billingKeyResponse) {
        return new Billing(
                memberId,
                billingKeyResponse.getCustomerKey(),
                billingKeyResponse.getBillingKey()
        );
    }

    public BillingKeyRequest saveBillingRequestToBillingKeyRequest(SaveBillingRequest saveBillingRequest) {
        return new BillingKeyRequest(
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

    public AutoPayRequest toAutoPayRequest(Billing billing, String orderId) {
        return new AutoPayRequest(
                9900,
                billing.getCustomerKey(),
                orderId,
                "[밀리의 서재+] 정기 구독"
        );
    }

    public Payment toPaymentEntity(Long memberId, PaymentResponse request) {
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
        return new PaymentEntityResponse(

        );
    }
}
