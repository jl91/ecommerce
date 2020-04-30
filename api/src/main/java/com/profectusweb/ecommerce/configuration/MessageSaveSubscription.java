package com.profectusweb.ecommerce.configuration;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageSaveSubscription {
    private CountDownLatch latch = new CountDownLatch(1);

    public void subscribe(byte[] message) {
        String value = new String(message);
        System.out.println("Received <" + value + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
