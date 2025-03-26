package com.example.paymentserver.payment.client;

import com.example.paymentserver.payment.dto.request.TossAutoPayRequest;
import com.example.paymentserver.payment.dto.request.TossBillingKeyRequest;
import com.example.paymentserver.payment.dto.request.TossRefundRequest;
import com.example.paymentserver.payment.dto.response.TossBillingKeyResponse;
import com.example.paymentserver.payment.dto.response.TossPaymentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
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

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                })
                .build();

        this.webClient = webClient
                .baseUrl("https://api.tosspayments.com/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.AUTHORIZATION, endcodedKey)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    private String makeAuthorizationKey(String secret) {
        String toEncode = secret+":";
        String encoded = Base64.getEncoder().encodeToString(toEncode.getBytes());

        return "Basic " + encoded;
    }

    // Make Billing Key
    public Mono<TossBillingKeyResponse> makeBillingKey(String customerKey, String authKey) {
        return webClient.post()
                .uri("/billing/authorizations/issue")
                .bodyValue(new TossBillingKeyRequest(customerKey, authKey))
                .retrieve()
                .bodyToMono(TossBillingKeyResponse.class);
    }

    // paySubscription
    public Mono<TossPaymentResponse> paySubscription(String billingKey, TossAutoPayRequest tossAutoPayRequest) {
        return webClient.post()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/billing/{billingKey}")
                                .build(billingKey)
                )
                .bodyValue(tossAutoPayRequest)
                .retrieve()
                .bodyToMono(TossPaymentResponse.class);
    }

    //refundSubscription
    public Mono<TossPaymentResponse> refundPayment(String paymentKey, TossRefundRequest request) {
        return webClient.post()
                .uri(
                        uriBuilder -> uriBuilder.path("/payments/{paymentKey/cancel")
                                .build(paymentKey)
                )
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TossPaymentResponse.class);
    }
}
