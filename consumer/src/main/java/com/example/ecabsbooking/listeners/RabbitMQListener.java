package com.example.ecabsbooking.listeners;

import DTO.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQListener {

    @RabbitListener(queues = "${rabbitmq.queue.audit}")
    public void receiveAuditMessage(Booking booking, @Header("action") String header) {
        System.out.println(header + " " + booking.toString());
        log.info(booking.toString());
    }

    @RabbitListener(queues = "${rabbitmq.queue.add}")
    public void receiveAddMessage(Booking booking) {
        System.out.println("ADDD " + booking.getPassengerName());
    }

    @RabbitListener(queues = "${rabbitmq.queue.edit}")
    public void receiveEditMessage(Booking booking) {
        System.out.println("EDIT " + booking.getPassengerName());
    }
}
