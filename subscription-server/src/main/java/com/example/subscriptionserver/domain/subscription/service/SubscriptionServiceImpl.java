package com.example.subscriptionserver.domain.subscription.service;

import com.example.subscriptionserver.domain.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
}
