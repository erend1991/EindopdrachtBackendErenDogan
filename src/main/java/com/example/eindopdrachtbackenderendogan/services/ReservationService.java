package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.ReservationMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Reservation;
import com.example.eindopdrachtbackenderendogan.repositories.ReservationRepository;

public class ReservationService {


    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
    public ReservationOutputDto createReservation(ReservationInputDto reservationInputDto){
        Reservation reservation = ReservationMapper.ReservationMapper.fromInputDtoToModel(reservationInputDto);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.fromModelToOutputDto(reservation);

    }
}
