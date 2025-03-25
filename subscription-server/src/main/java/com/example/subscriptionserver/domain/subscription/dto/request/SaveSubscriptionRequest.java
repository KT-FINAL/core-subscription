package com.example.subscriptionserver.domain.subscription.dto.request;

import com.example.subscriptionserver.domain.subscription.entity.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveSubscriptionRequest {
    private Long memberId;
    private SubscriptionType subscriptionType;
    private LocalDate subscriptionStartDate;
}
