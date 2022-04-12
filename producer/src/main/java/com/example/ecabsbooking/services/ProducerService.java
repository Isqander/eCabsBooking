package com.example.ecabsbooking.services;

import DTO.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.message}")
    private String exchangeMessage;

    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addBooking(Booking booking) {
        try {
            rabbitTemplate.convertAndSend(exchangeMessage, "", booking, m -> {
                m.getMessageProperties().getHeaders().put("action", "add");
                return m;
            });
        } catch (AmqpException e) {
            log.error("Error sending to RabbitMQ", e);
            e.printStackTrace();
        }
    }

    public void editBooking(Booking booking) {
        try {
            rabbitTemplate.convertAndSend(exchangeMessage,"", booking, m -> {
                m.getMessageProperties().getHeaders().put("action", "edit");
                return m;
            });
        } catch (AmqpException e) {
            log.error("Error sending to RabbitMQ", e);
            e.printStackTrace();
        }
    }

    public void deleteBooking(Long id) {
        try {
            rabbitTemplate.convertAndSend(exchangeMessage,"", id, m -> {
                m.getMessageProperties().getHeaders().put("action", "delete");
                return m;
            });
        } catch (AmqpException e) {
            log.error("Error sending to RabbitMQ", e);
            e.printStackTrace();
        }
    }
}
