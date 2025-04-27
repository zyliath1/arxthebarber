package com.example.application.services;

import com.example.application.data.Booking;
import com.example.application.data.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }
}
