package com.example.application.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Barber barber;

    @ManyToOne
    private ServiceItem service;

    private LocalDateTime aika;

    // Getters & setters
}

