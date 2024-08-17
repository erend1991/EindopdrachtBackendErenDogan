package com.example.eindopdrachtbackenderendogan.controllers;


import com.example.eindopdrachtbackenderendogan.services.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservations")
public class ReservationsController {

    private final ReservationService reservationService;

    public ReservationsController(ReservationService reservationService){
        this.reservationService = reservationService;

    }



}
