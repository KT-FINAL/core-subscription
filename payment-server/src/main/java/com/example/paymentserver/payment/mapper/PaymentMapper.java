package com.example.paymentserver.payment.mapper;

import com.example.paymentserver.payment.dto.request.BillingKeyRequest;
import com.example.paymentserver.payment.dto.request.SaveBillingRequest;
import com.example.paymentserver.payment.dto.response.BillingKeyResponse;
import com.example.paymentserver.payment.entity.Billing;
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


}
