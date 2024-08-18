package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    Long id;

    private String reservationName;

    private int guests;
    private int TableNumber;
    private LocalDateTime reservationTime;

    public Reservation(Long id, String reservationName, int guests, int tableNumber, LocalDateTime reservationTime) {
        this.id = id;
        this.reservationName = reservationName;
        this.guests = guests;
        TableNumber = tableNumber;
        this.reservationTime = reservationTime;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
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