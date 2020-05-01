package com.profectusweb.ecommerce.services.amqp;

import com.profectusweb.ecommerce.configuration.RabbitMQConfiguration;
import com.profectusweb.ecommerce.messages.SaveMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("UserAmqpService")
public class UserAmqpService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUpdateMessage(BigInteger id) {
        SaveMessage saveMessage = new SaveMessage();
        saveMessage.setId(id);
        saveMessage.setEntity("users");

        this.getConfiguredRabbitTemplate()
                .convertAndSend(saveMessage);
    }

    private RabbitTemplate getConfiguredRabbitTemplate() {
        this.rabbitTemplate
                .setRoutingKey(RabbitMQConfiguration.saveQueueName);

        this.rabbitTemplate
                .setExchange(RabbitMQConfiguration.topicExchangeName);

        return this.rabbitTemplate;
    }

}
