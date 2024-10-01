package com.example.eindopdrachtbackenderendogan.dtos.input;

import java.time.LocalDateTime;

public class ReservationInputDto {

    public String reservationName;
    public LocalDateTime reservationTime;
    public int tableNumber;

    public int guests;

    public String phoneNumber;

    public ReservationInputDto(String reservationName, LocalDateTime reservationTime,int tableNumber, int guests, String phoneNumber) {
        this.reservationName = reservationName;
        this.reservationTime = reservationTime;
        this.tableNumber = tableNumber;
        this.guests = guests;
        this.phoneNumber = phoneNumber;
    }

    public ReservationInputDto() {

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

    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
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