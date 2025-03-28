package com.example.subscriptionserver.domain.subscription.repository;

import com.example.subscriptionserver.domain.subscription.entity.Subscription;
import com.example.subscriptionserver.domain.subscription.entity.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s WHERE s.memberId = :memberId AND s.subscriptionStatus = :status")
    Optional<Subscription> findStatusSubscriptionByMemberId(@Param("memberId") Long memberId, @Param("status") SubscriptionStatus status);

    Boolean existsAllByMemberIdAndSubscriptionStatus(Long memberId, SubscriptionStatus status);

    List<Subscription> findAllByMemberId(Long memberId);

    @Query(
        "SELECT s FROM Subscription s WHERE s.willBeExpire = true AND s.subscriptionStatus = :status and s.endDate < :today"
    )
    List<Subscription> findExpiringSubscriptions(
            @Param("totay") LocalDate today,
            @Param("status") SubscriptionStatus status
            );
}
