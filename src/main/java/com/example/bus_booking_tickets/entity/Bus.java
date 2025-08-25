package com.example.bus_booking_tickets.entity;

//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;   // e.g. "BUS-101"
    private int capacity;
    private String kind; // e.g. "AC", "Mini", "Luxury"
}
