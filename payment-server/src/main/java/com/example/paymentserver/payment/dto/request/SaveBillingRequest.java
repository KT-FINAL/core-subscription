package com.example.paymentserver.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveBillingRequest {
    private Long memberId;
    private String customerKey;
    private String authKey;
}
