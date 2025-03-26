package com.example.paymentserver.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TossAutoPayRequest {
    private Integer amount;
    private String customerKey;
    private String orderId;
    private String orderName;
}
