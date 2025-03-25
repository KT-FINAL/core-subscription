package com.example.paymentserver.payment.controller;

import com.example.paymentserver.payment.dto.request.SaveBillingRequest;
import com.example.paymentserver.payment.dto.request.SavePaymentRequest;
import com.example.paymentserver.payment.dto.response.BillingResponse;
import com.example.paymentserver.payment.dto.response.PaymentEntityResponse;
import com.example.paymentserver.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/billing")
    public ResponseEntity<BillingResponse> saveBilling(@RequestBody SaveBillingRequest request) {
        return ResponseEntity.ok(paymentService.saveBilling(request));
    }

    @PostMapping
    public ResponseEntity<PaymentEntityResponse> savePayment(@RequestBody SavePaymentRequest request) {
        return ResponseEntity.ok(paymentService.savePayment(request));
    }
}
