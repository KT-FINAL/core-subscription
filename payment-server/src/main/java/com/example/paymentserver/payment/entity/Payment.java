package com.example.paymentserver.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String lastTransactionKey;

    private String paymentKey;

    private String orderId;

    private String orderName;

    private String status;

    private OffsetDateTime requestedAt;

    private OffsetDateTime approvedAt;

    private Integer totalAmount;

    public Payment (
            Long memberId, String lastTransactionKey, String paymentKey,
            String orderId, String orderName, String status,
            OffsetDateTime requestedAt, OffsetDateTime approvedAt, Integer totalAmount) {
        this.memberId = memberId;
        this.lastTransactionKey = lastTransactionKey;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.orderName = orderName;
        this.status = status;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
        this.totalAmount = totalAmount;
    }
}
