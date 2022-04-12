package com.example.ecabsbooking.mappers;

import DTO.Booking;
import DTO.TripWayPoint;
import com.example.ecabsbooking.Entity.BookingEntity;
import com.example.ecabsbooking.Entity.TripWayPointEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BookingMapper {
    Booking bookingToDTO (BookingEntity bookingEntity);
    TripWayPoint bookingToDTO (TripWayPointEntity tripWayPointEntity);
    BookingEntity bookingToEntity (Booking bookingDTO);
    TripWayPointEntity bookingToEntity (TripWayPoint TripWayPointDTO);

}
