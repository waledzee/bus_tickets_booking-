package com.example.bus_booking_tickets.service;

import com.example.bus_booking_tickets.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bus_booking_tickets.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setSeatNumber(updatedBooking.getSeatNumber());
            booking.setBookingDate(updatedBooking.getBookingDate());
            booking.setTrip(updatedBooking.getTrip());
            booking.setUser(updatedBooking.getUser());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
