package com.example.ecabsbooking.configuration;

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
    public FanoutExchange exchangeMessage(){
        return ExchangeBuilder.fanoutExchange(exchangeMessage).build();
    }

    @Bean
    public HeadersExchange exchangeBooking(){
        return ExchangeBuilder.headersExchange(exchangeBooking).build();
    }

    @Bean
    public Binding bindingExchangeToExchange(){
        return BindingBuilder.bind(exchangeBooking()).to(exchangeMessage());
    }

    @Bean
    public Binding bindingAuditQueue(){
        return BindingBuilder.bind(messageAuditQueue()).to(exchangeMessage());
    }

    @Bean
    public Binding bindingAddQueue(){
        return BindingBuilder.bind(bookingAddQueue()).to(exchangeBooking()).where("action").matches("add");
    }

    @Bean
    public Binding bindingEditQueue(){
        return BindingBuilder.bind(bookingEditQueue()).to(exchangeBooking()).where("action").matches("edit");
    }

    @Bean
    public Binding bindingDeleteQueue(){
        return BindingBuilder.bind(bookingDeleteQueue()).to(exchangeBooking()).where("action").matches("delete");
    }


}
