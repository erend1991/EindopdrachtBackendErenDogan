package com.example.eindopdrachtbackenderendogan.repositories;

import com.example.eindopdrachtbackenderendogan.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    }

