package com.example.bus_booking_tickets.controller;

import com.example.bus_booking_tickets.entity.Bus;
import com.example.bus_booking_tickets.service.BusService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    // Get all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    // Create new bus
    @PostMapping
    public Bus createBus(@RequestBody Bus bus) {
        return busService.createBus(bus);
    }

    // Get bus by id
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        return busService.getBusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update bus
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus updatedBus) {
        try {
            return ResponseEntity.ok(busService.updateBus(id, updatedBus));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete bus
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }
}
