package com.example.paymentserver.payment.repository;

import com.example.paymentserver.payment.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Optional<Billing> findByMemberId(Long memberId);
}
