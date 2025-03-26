package com.example.subscriptionserver.domain.subscription.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionFailedEvent {
    private Long paymentId;
    private Long memberId;
}
