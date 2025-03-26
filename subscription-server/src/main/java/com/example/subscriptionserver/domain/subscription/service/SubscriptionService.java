package com.example.subscriptionserver.domain.subscription.service;

import com.example.subscriptionserver.domain.subscription.dto.event.PaymentCompleteEvent;
import com.example.subscriptionserver.domain.subscription.dto.event.SubscriptionFailedEvent;
import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import com.example.subscriptionserver.domain.subscription.entity.SubscriptionStatus;
import com.example.subscriptionserver.domain.subscription.mapper.SubscriptionMapper;
import com.example.subscriptionserver.domain.subscription.repository.SubscriptionRepository;
import com.example.subscriptionserver.global.kafka.KafkaDtoMapper;
import com.example.subscriptionserver.global.kafka.SubscriptionKafkaProducer;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionKafkaProducer subscriptionKafkaProducer;
    private final KafkaDtoMapper kafkaDtoMapper;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper,
            SubscriptionKafkaProducer subscriptionKafkaProducer, KafkaDtoMapper kafkaDtoMapper
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionKafkaProducer = subscriptionKafkaProducer;
        this.kafkaDtoMapper = kafkaDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getAllSubscriptionsByMemberId(Long memberId) {
        return subscriptionRepository.findAllByMemberId(memberId).stream().map(x -> subscriptionMapper.entityToSubscriptionResponse(x)).toList();
    }

    @Transactional(readOnly = true)
    public SubscriptionResponse getActiveSubscriptionByMemberId(Long memberId) {
        Subscription subscription = getSubscriptionByMemberId(memberId, SubscriptionStatus.ACTIVE);

        return subscriptionMapper.entityToSubscriptionResponse(subscription);
    }

    @Transactional
    public void registerSubscription(PaymentCompleteEvent event) {
        try {
            Subscription subscription = subscriptionRepository.save(subscriptionMapper.eventToSubscription(event));
        } catch (Exception e) {
            subscriptionKafkaProducer.sendEvent("subscription-failed",kafkaDtoMapper.makeSubscriptionFailedEvent(
                    new SubscriptionFailedEvent(
                            event.getPaymentId(),
                            event.getMemberId()
                    )
            ));
        }
    }

    @Transactional(readOnly = true)
    public Boolean hasStatusSubscription(Long memberId, SubscriptionStatus subscriptionStatus) {
        return subscriptionRepository.existsAllByMemberIdAndSubscriptionStatus(memberId, subscriptionStatus);
    }


    @Transactional
    public void unSubscribeSubscription(Long memberId) {
        Subscription subscription = getSubscriptionByMemberId(memberId, SubscriptionStatus.ACTIVE);
        subscription.updateSubscriptionStatus(SubscriptionStatus.UNSUBSCRIBE);
    }

    private Subscription getSubscriptionByMemberId(Long memberId, SubscriptionStatus subscriptionStatus) {
        return subscriptionRepository.findStatusSubscriptionByMemberId(memberId, subscriptionStatus).orElseThrow(EntityExistsException::new);
    }
}
