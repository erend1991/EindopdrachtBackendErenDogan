package com.example.eindopdrachtbackenderendogan.controllers;


import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;

    }

    @GetMapping()
    public ResponseEntity<List<ReservationOutputDto>> getAllReservations() {
        reservationService.getAllReservations();

        return ResponseEntity.ok().body(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationOutputDto> getReservation(@PathVariable("id") long id) {

        return ResponseEntity.ok().body(reservationService.getReservationById(id));
    }


    @PostMapping
    public ResponseEntity<ReservationOutputDto> createReservation(
            @Valid
            @RequestBody ReservationInputDto reservationInputDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        try {
            ReservationOutputDto reservationOutputDto = reservationService.createReservation(reservationInputDto, userDetails.getUsername());

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reservationOutputDto.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(reservationOutputDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReservationOutputDto> editReservationById(@Valid @RequestBody ReservationInputDto newReservation, @PathVariable long id) {
        ReservationOutputDto dto = reservationService.editReservationById(id, newReservation);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservationById(@PathVariable long id) {

        reservationService.deleteReservationById(id);
        return ResponseEntity.ok(("Reservation deleted succesfull with id" + id));
    }


}
