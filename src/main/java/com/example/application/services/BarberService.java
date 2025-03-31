package com.example.application.services;

import com.example.application.data.Barber;
import com.example.application.data.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    public List<Barber> findAll() {
        return barberRepository.findAll();
    }

    public Barber save(Barber barber) {
        return barberRepository.save(barber);
    }

    public void delete(Barber barber) {
        barberRepository.delete(barber);
    }
}
