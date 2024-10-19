package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Reservation;

public class ReservationMapper {

    public static Reservation fromInputDtoToModel(ReservationInputDto reservationInputDto) {
        Reservation reservation = new Reservation();

        reservation.setReservationName(reservationInputDto.getReservationName());
        reservation.setGuests(reservationInputDto.getGuests());
        reservation.setTableNumber(reservationInputDto.getTableNumber());
        reservation.setReservationTime(reservationInputDto.getReservationTime());
        reservation.setPhoneNumber(reservationInputDto.getPhoneNumber());


        return reservation;
    }

    public static ReservationOutputDto fromModelToOutputDto(Reservation reservation) {
        ReservationOutputDto reservationOutputDto = new ReservationOutputDto();

        reservationOutputDto.setId(reservation.getId());
        reservationOutputDto.setReservationName(reservation.getReservationName());
        reservationOutputDto.setGuests(reservation.getGuests());
        reservationOutputDto.setTableNumber(reservation.getTableNumber());
        reservationOutputDto.setReservationTime(reservation.getReservationTime());
        reservationOutputDto.setPhoneNumber(reservation.getPhoneNumber());

        return reservationOutputDto;
    }
}
