package com.example.ecabsbooking.controllers;

import DTO.Booking;
import com.example.ecabsbooking.services.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/bookings")
public class ProducerController {
    private ProducerService producerService;

    @PostMapping
    public ResponseEntity addBooking(@RequestBody Booking booking) {
        producerService.addBooking(booking);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity editBooking(@RequestBody Booking booking) {
        producerService.editBooking(booking);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id) {
        producerService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
}
