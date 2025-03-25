package com.example.subscriptionserver.domain.subscription.service;

import com.example.subscriptionserver.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import com.example.subscriptionserver.domain.subscription.entity.SubscriptionStatus;
import com.example.subscriptionserver.domain.subscription.mapper.SubscriptionMapper;
import com.example.subscriptionserver.domain.subscription.repository.SubscriptionRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getAllSubscriptionsByMemberId(Long memberId) {
        return subscriptionRepository.findAllByMemberId(memberId).stream().map(x -> subscriptionMapper.entityToSubscriptionResponse(x)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponse getActiveSubscriptionByMemberId(Long memberId) {
        Subscription subscription = getSubscriptionByMemberId(memberId, SubscriptionStatus.ACTIVE);

        return subscriptionMapper.entityToSubscriptionResponse(subscription);
    }

    @Override
    public void registerSubscription(SaveSubscriptionRequest saveSubscriptionRequest) {
        Subscription subscription = subscriptionRepository.save(subscriptionMapper.saveRequestToSubscription(saveSubscriptionRequest));

    }

    @Override
    @Transactional(readOnly = true)
    public Boolean hasStatusSubscription(Long memberId, SubscriptionStatus subscriptionStatus) {
        return subscriptionRepository.existsAllByMemberIdAndSubscriptionStatus(memberId, subscriptionStatus);
    }


    @Override
    @Transactional
    public void unSubscribeSubscription(Long memberId) {
        Subscription subscription = getSubscriptionByMemberId(memberId, SubscriptionStatus.ACTIVE);
        subscription.updateSubscriptionStatus(SubscriptionStatus.UNSUBSCRIBE);
    }

    private Subscription getSubscriptionByMemberId(Long memberId, SubscriptionStatus subscriptionStatus) {
        return subscriptionRepository.findStatusSubscriptionByMemberId(memberId, subscriptionStatus).orElseThrow(EntityExistsException::new);
    }
}
