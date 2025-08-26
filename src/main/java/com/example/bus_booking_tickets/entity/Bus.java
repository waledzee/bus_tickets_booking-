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

    @Enumerated(EnumType.STRING)
    private BusStatus status = BusStatus.AVAILABLE;

    @OneToOne(mappedBy = "bus")
    private Trip trip;
    private String number;
    private int capacity;
    private String kind;



}
