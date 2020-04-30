package com.profectusweb.ecommerce.AMQPMessages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class SaveSubscription {
    private CountDownLatch latch = new CountDownLatch(1);

    public void subscribe(byte[] message) {
        try {
            String value = new String(message);
            ObjectMapper objectMapper = new ObjectMapper();

            SaveMessage saveMessage = objectMapper.readValue(value, SaveMessage.class);
            System.out.println("Received <" + saveMessage + ">");
            latch.countDown();

        } catch (Exception e) {

        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
