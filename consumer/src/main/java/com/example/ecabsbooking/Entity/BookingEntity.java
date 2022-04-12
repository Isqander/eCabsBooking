package com.example.ecabsbooking.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String passengerName;
    String passengerContactNumber;
    LocalDateTime pickupTime;
    Boolean asap;
    Long waitingTime;
    Integer numberOfPassengers;
    //in cents
    Integer price;
    Integer rating;
    LocalDateTime createdOn;
    LocalDateTime lastModifiedOn;

    @OneToMany
    List<TripWayPointEntity> tripWayPoints;
}
