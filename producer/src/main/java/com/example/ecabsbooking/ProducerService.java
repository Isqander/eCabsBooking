package com.example.ecabsbooking;

import com.example.ecabsbooking.DTO.Booking;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.message}")
    private String exchangeMessage;

    @Value("${rabbitmq.routingKey.add}")
    private String routingKeyAdd;

    @Value("${rabbitmq.routingKey.edit}")
    private String routingKeyEdit;

    @Value("${rabbitmq.routingKey.delete}")
    private String routingKeyDelete;

    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addBooking(Booking booking) {
        rabbitTemplate.convertAndSend(exchangeMessage, routingKeyAdd, booking);
    }

    public void editBooking(Booking booking) {
    }

    public void deleteBooking(Long id) {
    }
}
