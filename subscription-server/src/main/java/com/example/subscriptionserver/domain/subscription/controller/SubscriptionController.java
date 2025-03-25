package com.example.subscriptionserver.domain.subscription.controller;

import com.example.subscriptionserver.domain.subscription.dto.response.SubscriptionResponse;
import com.example.subscriptionserver.domain.subscription.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/all/{memberId}")
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptionsByMemberId(memberId));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<SubscriptionResponse> getMyActiveSubscription(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(
                subscriptionService.getActiveSubscriptionByMemberId(memberId)
        );
    }

    @PutMapping("/unsubscribe/{memberId}")
    public void unsubscribeMember(@PathVariable("memberId") Long memberId) {
        subscriptionService.unSubscribeSubscription(memberId);
    }
}
