package com.example.paymentserver.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String customerKey;

    private String billingKey;

    public  Billing(Long memberId, String customerKey, String billingKey) {
        this.memberId = memberId;
        this.customerKey = customerKey;
        this.billingKey = billingKey;
    }
}
