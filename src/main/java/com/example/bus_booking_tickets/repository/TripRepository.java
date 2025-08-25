package com.example.bus_booking_tickets.repository;

import com.example.bus_booking_tickets.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TripRepository extends JpaRepository<Trip,Long> {
}
