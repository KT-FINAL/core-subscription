package com.example.paymentserver.global.kafka;

import com.example.paymentserver.payment.dto.event.PaymentCompleteEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class KafkaDtoMapper {
    public String makePaymentCompleteEvent(PaymentCompleteEvent event){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(event);
        }catch (Exception e){
            return "NULL";
        }
    }

}
