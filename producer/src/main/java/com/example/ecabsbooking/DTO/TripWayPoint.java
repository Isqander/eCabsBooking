package com.example.ecabsbooking.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class TripWayPoint implements Serializable {
    Long id;
    String locality;
    Double latitude;
    Double longitude;
}
