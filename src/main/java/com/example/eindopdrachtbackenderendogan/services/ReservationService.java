package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.ReservationMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.Reservation;
import com.example.eindopdrachtbackenderendogan.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationOutputDto createReservation(ReservationInputDto reservationInputDto) {
        Reservation r = reservationRepository.save(ReservationMapper.fromInputDtoToModel(reservationInputDto));
        return ReservationMapper.fromModelToOutputDto(r);
    }


    public List<ReservationOutputDto> getAllReservations() {
        List<Reservation> allReservations = reservationRepository.findAll();
        List<ReservationOutputDto> allReservationsOutput = new ArrayList<>();

        for (Reservation r : allReservations) {
            allReservationsOutput.add(ReservationMapper.fromModelToOutputDto(r));

        }
        return allReservationsOutput;

    }

    public ReservationOutputDto getReservationById(long id) {
        Optional<Reservation> r = reservationRepository.findById(id);
        if (r.isPresent()) {
            return ReservationMapper.fromModelToOutputDto(r.get());
        } else {
            throw new RecordNotFoundException("no reservation found with id" + id);
        }
    }
    public ReservationOutputDto editReservationById(long id, ReservationInputDto newReservation){
        Optional<Reservation> r = reservationRepository.findById(id);
        if (r.isPresent()){
            Reservation reservation = r.get();
            reservation.setReservationName(newReservation.reservationName);
            reservation.setReservationTime(newReservation.reservationTime);
            reservation.setTableNumber(newReservation.tableNumber);
            reservation.setGuests(newReservation.guests);
            reservation.setPhoneNumber(newReservation.phoneNumber);

            reservationRepository.save(reservation);

            return ReservationMapper.fromModelToOutputDto(reservation);
        } else {
            throw new RecordNotFoundException("No reservation added");
        }
    }
    public void deleteReservationById(long id){
        if(reservationRepository.existsById(id)){
            reservationRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("No reservation found with id" + id);
        }
    }

}
