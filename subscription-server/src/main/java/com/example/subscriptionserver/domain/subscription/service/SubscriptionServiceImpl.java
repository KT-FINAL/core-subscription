package com.example.subscriptionserver.domain.subscription.service;

import com.example.subscriptionserver.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import com.example.subscriptionserver.domain.subscription.mapper.SubscriptionMapper;
import com.example.subscriptionserver.domain.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(
            SubscriptionRepository subscriptionRepository,
            SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public List<SubscriptionResponse> getAllSubscriptionsByMemberId(Long memberId) {
        return List.of();
    }

    @Override
    public SubscriptionResponse getActiveSubscriptionByMemberId(Long memberId) {
        return null;
    }

    @Override
    public void registerSubscription(SaveSubscriptionRequest saveSubscriptionRequest) {
        Subscription subscription = subscriptionRepository.save(
                subscriptionMapper.saveRequestToSubscription(saveSubscriptionRequest)
        );

    }

    @Override
    public void hasActiveSubscription(Long memberId) {

    }

    @Override
    public void unSubscribeSubscription(Long memberId) {

    }
}
