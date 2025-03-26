package com.example.paymentserver.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KT-FINAL-PAYMENT-SERVER")
                        .version("v1.0.0")
                        .description("API documentation with Swagger and Springdoc"));
    }
}
