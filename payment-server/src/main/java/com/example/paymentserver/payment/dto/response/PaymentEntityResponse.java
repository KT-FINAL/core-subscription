package com.example.paymentserver.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntityResponse {
    private Long id;
    private Long memberId;
    private String lastTransactionKey;
    private String paymentKey;
    private String orderId;
    private String orderName;
    private String status;
    private OffsetDateTime requestedAt;
    private OffsetDateTime approvedAt;
    private Integer totalAmount;
}
