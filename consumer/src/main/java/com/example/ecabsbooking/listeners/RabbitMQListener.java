package com.example.ecabsbooking.listeners;

import DTO.Booking;
import com.example.ecabsbooking.mappers.BookingMapper;
import com.example.ecabsbooking.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitMQListener {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;

    @RabbitListener(queues = "${rabbitmq.queue.audit}")
    public void receiveAuditMessage(Booking booking, @Header("action") String action) {
        System.out.println("We " + action + " a booking: " + booking);
        log.info("We " + action + " a booking: " + booking);
    }

    @RabbitListener(queues = "${rabbitmq.queue.add}")
    public void receiveAddMessage(Booking booking) {
        bookingRepository.saveAndFlush(bookingMapper.bookingToEntity(booking));
        System.out.println("We ADDED a booking: " + booking);
    }

    @RabbitListener(queues = "${rabbitmq.queue.edit}")
    public void receiveEditMessage(Booking booking) {
        bookingRepository.saveAndFlush(bookingMapper.bookingToEntity(booking));
    }

    @RabbitListener(queues = "${rabbitmq.queue.delete}")
    public void receiveDeleteMessage(Long id) {
        bookingRepository.deleteById(id);
    }
}
