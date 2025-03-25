package com.example.paymentserver.payment.client;

import com.example.paymentserver.payment.dto.request.AutoPayRequest;
import com.example.paymentserver.payment.dto.request.BillingKeyRequest;
import com.example.paymentserver.payment.dto.response.BillingKeyResponse;
import com.example.paymentserver.payment.dto.response.PaymentResponse;
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
                .baseUrl("https://api.tosspayments.com/v1/billing")
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
    public Mono<BillingKeyResponse> makeBillingKey(String customerKey, String authKey) {
        return webClient.post()
                .uri("/authorizations/issue")
                .bodyValue(new BillingKeyRequest(customerKey, authKey))
                .retrieve()
                .bodyToMono(BillingKeyResponse.class);
    }

    // paySubscription
    public Mono<PaymentResponse> paySubscription(String billingKey, AutoPayRequest autoPayRequest) {
        return webClient.post()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/{billingKey}")
                                .build(billingKey)
                )
                .bodyValue(autoPayRequest)
                .retrieve()
                .bodyToMono(PaymentResponse.class);
    }
}
