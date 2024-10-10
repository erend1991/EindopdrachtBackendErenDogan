package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue
    Long id;

    private String reservationName;

    private LocalDateTime reservationTime;
    private int guests;
    private int TableNumber;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;



    public Reservation(Long id, String reservationName, int guests, int tableNumber, LocalDateTime reservationTime, String phoneNumber) {
        this.id = id;
        this.reservationName = reservationName;
        this.guests = guests;
        TableNumber = tableNumber;
        this.reservationTime = reservationTime;
        this.phoneNumber = phoneNumber;
    }

    public Reservation() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

