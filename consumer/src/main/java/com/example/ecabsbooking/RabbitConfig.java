package com.example.ecabsbooking;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.audit}")
    private String queueAudit;

    @Value("${rabbitmq.queue.delete}")
    private String queueDelete;

    @Value("${rabbitmq.queue.edit}")
    private String queueEdit;

    @Value("${rabbitmq.queue.add}")
    private String queueAdd;

    private ConnectionFactory connectionFactory;
    private RabbitMQListener rabbitMQListener;

    public RabbitConfig(ConnectionFactory connectionFactory, RabbitMQListener rabbitMQListener) {
        this.connectionFactory = connectionFactory;
        this.rabbitMQListener = rabbitMQListener;
    }

    //    @Value("${spring.rabbitmq.host}")
//    private String rabbitmqHost;

    @Bean
    public Queue messageAuditQueue(){
        return QueueBuilder.durable(queueAudit).build();
    }

    @Bean
    public Queue bookingAddQueue(){
        return QueueBuilder.durable(queueAdd).build();
    }

    @Bean
    public Queue bookingEditQueue(){
        return QueueBuilder.durable(queueEdit).build();
    }

    @Bean
    public Queue bookingDeleteQueue(){
        return QueueBuilder.durable(queueDelete).build();
    }


//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost);
//        connectionFactory
//        return new Queue(queue);
//    }

    @Bean
    public MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setQueues(bookingAddQueue());
        messageListenerContainer.setMessageListener(rabbitMQListener);
        return messageListenerContainer;
    }


}
