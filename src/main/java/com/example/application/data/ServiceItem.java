package com.example.application.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String kuvaus;
    private Double hinta;
    private Integer kestoMinuuteissa;
    @ManyToMany
    private List<Barber> barbers;

    // Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public Double getHinta() {
        return hinta;
    }

    public void setHinta(Double hinta) {
        this.hinta = hinta;
    }

    public Integer getKestoMinuuteissa() {
        return kestoMinuuteissa;
    }

    public void setKestoMinuuteissa(Integer kestoMinuuteissa) {
        this.kestoMinuuteissa = kestoMinuuteissa;
    }

    public List<Barber> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<Barber> barbers) {
        this.barbers = barbers;
    }
}

