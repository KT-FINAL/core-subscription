package com.example.subscriptionserver.domain.subscription.service;

import com.example.subscriptionserver.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;


import java.util.List;

public interface SubscriptionService {
    List<SubscriptionResponse> getAllSubscriptionsByMemberId(Long memberId);

    SubscriptionResponse getActiveSubscriptionByMemberId(Long memberId);

    void registerSubscription(SaveSubscriptionRequest saveSubscriptionRequest);

    void hasActiveSubscription(Long memberId);

    void unSubscribeSubscription(Long memberId);
}
