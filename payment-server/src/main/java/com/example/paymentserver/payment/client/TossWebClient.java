package com.example.paymentserver.payment.client;

import com.example.paymentserver.payment.dto.request.AutoPayRequest;
import com.example.paymentserver.payment.dto.request.BillingKeyRequest;
import com.example.paymentserver.payment.dto.response.BillingKeyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Component
public class TossWebClient {
    private final WebClient webClient;

    @Value("${toss.client-key}")
    private String client;

    public TossWebClient(WebClient.Builder webClient,
                         @Value("${toss.secret-key}") String secret) {
        String endcodedKey = makeAuthorizationKey(secret);

        this.webClient = webClient
                .baseUrl("https://api.tosspayments.com/v1/billing")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.AUTHORIZATION, endcodedKey)
                .build();
    }

    private String makeAuthorizationKey(String secret) {
        String toEncode = secret+":";
        String encoded = Base64.getEncoder().encodeToString(toEncode.getBytes());

        return "Basic " + encoded;
    }

    // Make Billing Key
    public Mono<BillingKeyResponse> makeBillingKey(String customerKey, String authKey) {
        return webClient.post()
                .uri("/authorizations/issue")
                .bodyValue(new BillingKeyRequest(customerKey, authKey))
                .retrieve()
                .bodyToMono(BillingKeyResponse.class);
    }

    //
    public void paySubscription(String billingKey, AutoPayRequest autoPayRequest) {
        webClient.post()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/{billingKey}")
                                .build(billingKey)
                )
                .bodyValue(autoPayRequest)
                .retrieve()
                .bodyToMono(String.class);
    }
}
