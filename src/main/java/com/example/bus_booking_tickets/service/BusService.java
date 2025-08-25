package com.example.bus_booking_tickets.service;
import com.example.bus_booking_tickets.entity.Bus;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bus_booking_tickets.repository.BusRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    public Bus updateBus(Long id, Bus updatedBus) {
        return busRepository.findById(id).map(bus -> {
            bus.setNumber(updatedBus.getNumber());
            bus.setCapacity(updatedBus.getCapacity());
            bus.setKind(updatedBus.getKind());
            return busRepository.save(bus);
        }).orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}
