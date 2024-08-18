package com.example.eindopdrachtbackenderendogan.dtos.input;

import java.time.LocalDateTime;

public class ReservationInputDto {

    public String ReservationName;
    public LocalDateTime reservationTime;

    public int tableNumber;

    public int guests;

    public ReservationInputDto(String reservationName, LocalDateTime reservationTime,int tableNumber, int guests) {
        ReservationName = reservationName;
        this.reservationTime = reservationTime;
        this.tableNumber = tableNumber;
        this.guests = guests;
    }

    public String getReservationName() {
        return ReservationName;
    }

    public void setReservationName(String reservationName) {
        ReservationName = reservationName;
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
}