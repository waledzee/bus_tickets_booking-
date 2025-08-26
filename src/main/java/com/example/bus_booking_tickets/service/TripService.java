package com.example.bus_booking_tickets.service;

import com.example.bus_booking_tickets.entity.Bus;
import com.example.bus_booking_tickets.entity.BusStatus;
import com.example.bus_booking_tickets.entity.Trip;
import com.example.bus_booking_tickets.entity.TripStatus;
import com.example.bus_booking_tickets.repository.BusRepository;
import com.example.bus_booking_tickets.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    // get all trips
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // create trip with logic
    public Trip createTrip(Trip trip) {
        Bus bus = busRepository.findById(trip.getBus().getId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        // Check if bus already has an ongoing trip
        boolean hasOngoingTrip = tripRepository.existsByBusAndStatus(bus, TripStatus.ONGOING);
        if (hasOngoingTrip) {
            throw new RuntimeException("Bus is already assigned to another ongoing trip");
        }

        // Set default status
        trip.setStatus(TripStatus.SCHEDULED);
        return tripRepository.save(trip);
    }

    // get trip by id
    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    // update trip (only if not started)
    public Trip updateTrip(Long id, Trip updatedTrip) {
        return tripRepository.findById(id).map(trip -> {
            if ("ONGOING".equals(trip.getStatus()) || "FINISHED".equals(trip.getStatus())) {
                throw new RuntimeException("Cannot update trip that is ongoing or finished");
            }
            trip.setSource(updatedTrip.getSource());
            trip.setDestination(updatedTrip.getDestination());
            trip.setDepartureTime(updatedTrip.getDepartureTime());
            trip.setArrivalTime(updatedTrip.getArrivalTime());
            trip.setPrice(updatedTrip.getPrice());
            trip.setBus(updatedTrip.getBus());
            return tripRepository.save(trip);
        }).orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    // delete trip (only if not started)
    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (BusStatus.ONGOING.equals(trip.getStatus())) {
            throw new RuntimeException("Cannot delete an ongoing trip");
        }

        tripRepository.deleteById(id);
    }

    // start trip (moderator will use this)
    public Trip startTrip(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!"SCHEDULED".equals(trip.getStatus())) {
            throw new RuntimeException("Trip cannot be started. Current status: " + trip.getStatus());
        }

        trip.setStatus(TripStatus.ONGOING);
        trip.setDepartureTime(LocalDateTime.now());
        return tripRepository.save(trip);
    }

    // finish trip (moderator will use this)
    public Trip finishTrip(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!"ONGOING".equals(trip.getStatus())) {
            throw new RuntimeException("Only ongoing trips can be finished");
        }

        trip.setStatus(TripStatus.COMPLETED);
        trip.setArrivalTime(LocalDateTime.now());
        return tripRepository.save(trip);
    }
}
