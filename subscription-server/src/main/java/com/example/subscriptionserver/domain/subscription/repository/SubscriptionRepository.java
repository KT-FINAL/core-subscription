package com.example.subscriptionserver.domain.subscription.repository;

import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
