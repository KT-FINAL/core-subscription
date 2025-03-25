package com.example.subscriptionserver.domain.subscription.mapper;

import com.example.subscriptionserver.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
    public Subscription saveRequestToSubscription(SaveSubscriptionRequest request) {
        return new Subscription(
                request.getMemberId(),
                request.getSubscriptionType(),
                request.getSubscriptionStartDate()
        );
    }

    public SubscriptionResponse entityToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getMemberId(),
                subscription.getSubscriptionType(),
                subscription.getSubscriptionStatus(),
                subscription.getStartDate(),
                subscription.getEndDate()
        );
    }
}
