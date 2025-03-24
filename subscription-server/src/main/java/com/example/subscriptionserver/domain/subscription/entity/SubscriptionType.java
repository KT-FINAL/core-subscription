package com.example.subscriptionserver.domain.subscription.entity;

public enum SubscriptionType {
    BASIC(9900), PREMIUM(15900);

    private Integer fee;

    SubscriptionType(Integer fee){
        this.fee = fee;
    }
}
