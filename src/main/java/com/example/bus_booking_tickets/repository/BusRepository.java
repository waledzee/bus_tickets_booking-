package com.example.bus_booking_tickets.repository;

import com.example.bus_booking_tickets.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BusRepository extends JpaRepository<Bus, Long> {
}
