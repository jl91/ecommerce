package com.profectusweb.ecommerce.configuration;

import com.profectusweb.ecommerce.messages.SaveSubscription;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RabbitMQConfiguration {
    public static final String topicExchangeName = "ecommerce-exchange";

    public static final String saveQueueName = "save";

    public static final String saveRouting = "save";

    @Bean
    Queue queue() {
        return new Queue(saveQueueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(saveRouting);
    }

    @Bean
    SimpleMessageListenerContainer rabbitContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(saveQueueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(SaveSubscription receiver) {
        return new MessageListenerAdapter(receiver, "subscribe");
    }
}
