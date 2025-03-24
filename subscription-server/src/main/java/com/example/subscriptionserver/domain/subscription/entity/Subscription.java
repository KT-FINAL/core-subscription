package com.example.subscriptionserver.domain.subscription.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private LocalDate startDate;

    private LocalDate endDate;

    public Subscription(Long memberId, SubscriptionType subscriptionType, LocalDate startDate) {
        this.memberId = memberId;
        this.subscriptionType = subscriptionType;
        this.startDate = startDate;
        this.endDate = startDate.plusMonths(1);
    }

    // paymentId
}
