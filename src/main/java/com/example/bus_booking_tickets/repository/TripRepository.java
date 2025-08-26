package com.example.bus_booking_tickets.repository;

import com.example.bus_booking_tickets.entity.Bus;
import com.example.bus_booking_tickets.entity.Trip;
import com.example.bus_booking_tickets.entity.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TripRepository extends JpaRepository<Trip,Long> {
    boolean existsByBusAndStatus(Bus bus, TripStatus status);
}
