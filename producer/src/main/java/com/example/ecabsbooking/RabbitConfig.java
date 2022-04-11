package com.example.ecabsbooking;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange.message}")
    private String exchangeMessage;

    @Value("${rabbitmq.exchange.booking}")
    private String exchangeBooking;

    @Value("${rabbitmq.routingKey.add}")
    private String routingKeyAdd;

    @Value("${rabbitmq.routingKey.edit}")
    private String routingKeyEdit;

    @Value("${rabbitmq.routingKey.delete}")
    private String routingKeyDelete;

    @Value("${rabbitmq.queue.audit}")
    private String queueAudit;

    @Value("${rabbitmq.queue.delete}")
    private String queueDelete;

    @Value("${rabbitmq.queue.edit}")
    private String queueEdit;

    @Value("${rabbitmq.queue.add}")
    private String queueAdd;



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

    @Bean
    public Exchange exchangeMessage(){
        return ExchangeBuilder.fanoutExchange(exchangeMessage).build();
    }

    @Bean
    public Exchange exchangeBooking(){
        return ExchangeBuilder.topicExchange(exchangeBooking).build();
    }

    @Bean
    public Binding bindingExchangeToExchange(){
        return BindingBuilder.bind(exchangeBooking()).to(exchangeMessage()).with("any").noargs();
    }

    @Bean
    public Binding bindingAddQueue(){
        return BindingBuilder.bind(bookingAddQueue()).to(exchangeBooking()).with(routingKeyAdd).noargs();
    }

    @Bean
    public Binding bindingEditQueue(){
        return BindingBuilder.bind(bookingEditQueue()).to(exchangeBooking()).with(routingKeyEdit).noargs();
    }

    @Bean
    public Binding bindingDeleteQueue(){
        return BindingBuilder.bind(bookingDeleteQueue()).to(exchangeBooking()).with(routingKeyDelete).noargs();
    }


}
