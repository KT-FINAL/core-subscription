package com.example.paymentserver.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillingResponse {
    private Long id;
    private Long memberId;
    private String customerKey;
    private String billingKey;
}
