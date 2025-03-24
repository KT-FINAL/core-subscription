package com.example.subscriptionserver.domain.subscription.dto.response;

import com.example.subscriptionserver.domain.subscription.entity.SubscriptionStatus;
import com.example.subscriptionserver.domain.subscription.entity.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {
    private Long id;
    private Long memberId;
    private SubscriptionType subscriptionType;
    private SubscriptionStatus subscriptionStatus;
    private LocalDate startSubscriptionDate;
    private LocalDate endSubscriptionDate;
}
