package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.ReservationMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Profile;
import com.example.eindopdrachtbackenderendogan.models.Reservation;
import com.example.eindopdrachtbackenderendogan.models.User;
import com.example.eindopdrachtbackenderendogan.repositories.ProfileRepository;
import com.example.eindopdrachtbackenderendogan.repositories.ReservationRepository;
import com.example.eindopdrachtbackenderendogan.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProfileRepository profileRepository;

    @InjectMocks
    ReservationService reservationService;

    @Test
    @DisplayName("create new reservation")
    void createReservation() {

        ReservationInputDto reservationInputDto = new ReservationInputDto();
        reservationInputDto.setReservationName("birthday eren");
        reservationInputDto.setReservationTime(LocalDateTime.of(2024, 12, 17, 20, 0));
        reservationInputDto.setTableNumber(1);
        reservationInputDto.setGuests(5);
        reservationInputDto.setPhoneNumber(612345678);

        String username = "testUser";

        User user = new User();
        user.setUsername(username);

        Profile profile = new Profile();

        Reservation reservation = ReservationMapper.fromInputDtoToModel(reservationInputDto);
        reservation.setId(1L);


        Mockito.when(userRepository.findById(username)).thenReturn(Optional.of(user));
        Mockito.when(profileRepository.findByUser(user)).thenReturn(profile);
        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);

        ReservationOutputDto reservationOutputDto = reservationService.createReservation(reservationInputDto, username);


        assertEquals("birthday eren", reservationOutputDto.getReservationName());
        assertEquals(LocalDateTime.of(2024, 12, 17, 20, 0), reservationOutputDto.getReservationTime());
        assertEquals(1, reservationOutputDto.getTableNumber());
        assertEquals(5, reservationOutputDto.getGuests());
        assertEquals(612345678, reservationOutputDto.getPhoneNumber());

    }
}
