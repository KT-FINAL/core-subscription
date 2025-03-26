package com.example.subscriptionserver.global.kafka;

import com.example.subscriptionserver.domain.subscription.dto.event.SubscriptionFailedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class KafkaDtoMapper {
    public String makeSubscriptionFailedEvent(SubscriptionFailedEvent event){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(event);
        }catch (Exception e){
            return "NULL";
        }
    }
}
