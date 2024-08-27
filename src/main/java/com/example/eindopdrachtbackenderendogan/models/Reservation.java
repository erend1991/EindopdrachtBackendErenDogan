package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    Long id;

    private String reservationName;

    private LocalDateTime reservationTime;
    private int guests;
    private int TableNumber;
    private int phoneNumber;

    public Reservation(Long id, String reservationName, int guests, int tableNumber, LocalDateTime reservationTime, int phoneNumber) {
        this.id = id;
        this.reservationName = reservationName;
        this.guests = guests;
        TableNumber = tableNumber;
        this.reservationTime = reservationTime;
        this.phoneNumber = phoneNumber;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}