package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reservations {

    @Id
    Long id;

    private String Name;

    private int guests;
    private  int TableNumber;
    private LocalDateTime reservationTime;

    public Reservations(Long id, String name, int guests, int tableNumber, LocalDateTime reservationTime) {
        this.id = id;
        Name = name;
        this.guests = guests;
        TableNumber = tableNumber;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(int tableNumber) {
        TableNumber = tableNumber;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
