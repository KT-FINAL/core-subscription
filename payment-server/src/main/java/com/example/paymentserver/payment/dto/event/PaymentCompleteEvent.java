package com.example.paymentserver.payment.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompleteEvent {
    private Long paymentId;
    private Long memberId;
}
