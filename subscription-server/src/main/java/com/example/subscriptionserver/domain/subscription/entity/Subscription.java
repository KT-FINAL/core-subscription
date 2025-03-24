package com.example.subscriptionserver.domain.subscription.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    public Subscription(Long memberId, SubscriptionType subscriptionType) {
        this.memberId = memberId;
        this.subscriptionType = subscriptionType;
    }
}
