package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.repositories.ReservationRepository;

public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
}
