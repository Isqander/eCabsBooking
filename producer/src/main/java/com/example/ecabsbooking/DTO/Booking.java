package com.example.ecabsbooking.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Booking implements Serializable {
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
    List<TripWayPoint> tripWayPoints;
}
