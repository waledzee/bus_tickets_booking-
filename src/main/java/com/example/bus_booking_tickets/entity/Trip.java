package com.example.bus_booking_tickets.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String destination;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private double price;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
}


