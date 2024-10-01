package com.example.eindopdrachtbackenderendogan.dtos.output;

import java.time.LocalDateTime;

public class ReservationOutputDto {

    private long id;

    private String reservationName;
    private LocalDateTime reservationTime;

    private int tableNumber;
    private int guests;

    private String phoneNumber;

    public ReservationOutputDto(long id, String reservationName, LocalDateTime reservationTime, int tableNumber, int guests, String phoneNumber) {

        this.id = id;
        this.reservationName = reservationName;
        this.reservationTime = reservationTime;
        this.tableNumber = tableNumber;
        this.guests = guests;
        this.phoneNumber = phoneNumber;
    }

    public ReservationOutputDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tabelNumber) {
        this.tableNumber = tabelNumber;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}