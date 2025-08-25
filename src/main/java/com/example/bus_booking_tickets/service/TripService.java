package com.example.bus_booking_tickets.service;

import com.example.bus_booking_tickets.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bus_booking_tickets.repository.TripRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
        return tripRepository.findById(id).map(trip -> {
            trip.setSource(updatedTrip.getSource());
            trip.setDestination(updatedTrip.getDestination());
            trip.setDepartureTime(updatedTrip.getDepartureTime());
            trip.setArrivalTime(updatedTrip.getArrivalTime());
            trip.setPrice(updatedTrip.getPrice());
            trip.setBus(updatedTrip.getBus());
            return tripRepository.save(trip);
        }).orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}
