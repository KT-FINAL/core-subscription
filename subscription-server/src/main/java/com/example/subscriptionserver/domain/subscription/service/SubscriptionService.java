package com.example.subscriptionserver.domain.subscription.service;

import com.example.subscriptionserver.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.entity.SubscriptionStatus;


import java.util.List;

public interface SubscriptionService {
    List<SubscriptionResponse> getAllSubscriptionsByMemberId(Long memberId);

    SubscriptionResponse getActiveSubscriptionByMemberId(Long memberId);

    void registerSubscription(SaveSubscriptionRequest saveSubscriptionRequest);

    Boolean hasStatusSubscription(Long memberId, SubscriptionStatus subscriptionStatus);

    void unSubscribeSubscription(Long memberId);
}
