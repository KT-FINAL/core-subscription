package com.example.subscriptionserver.domain.subscription.mapper;

import com.example.subscriptionserver.domain.subscription.dto.event.PaymentCompleteEvent;
import com.example.subscriptionserver.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import com.example.subscriptionserver.domain.subscription.entity.SubscriptionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionMapper {

    public Subscription eventToSubscription(PaymentCompleteEvent event) {
        return new Subscription(
                event.getMemberId(),
                SubscriptionType.BASIC,
                LocalDate.now(),
                false
        );
    }

    public Subscription saveRequestToSubscription(SaveSubscriptionRequest request) {
        return new Subscription(
                request.getMemberId(),
                request.getSubscriptionType(),
                request.getSubscriptionStartDate(),
                false
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
