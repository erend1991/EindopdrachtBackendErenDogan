package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.ReservationMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceUnitTest {

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
    void shouldCreateReservation() {

        ReservationInputDto reservationInputDto = new ReservationInputDto();
        reservationInputDto.setReservationName("birthday eren");
        reservationInputDto.setReservationTime(LocalDateTime.of(2024, 12, 17, 20, 0));
        reservationInputDto.setTableNumber(1);
        reservationInputDto.setGuests(5);
        reservationInputDto.setPhoneNumber("612345678");

        String username = "testUser";

        User user = new User();
        user.setUsername(username);

        Profile profile = new Profile();

        Reservation reservation = ReservationMapper.fromInputDtoToModel(reservationInputDto);
        reservation.setId(1L);


        when(userRepository.findById(username)).thenReturn(Optional.of(user));
        when(profileRepository.findByUser(user)).thenReturn(profile);
        when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);

        ReservationOutputDto reservationOutputDto = reservationService.createReservation(reservationInputDto, username);


        assertEquals("birthday eren", reservationOutputDto.getReservationName());
        assertEquals(LocalDateTime.of(2024, 12, 17, 20, 0), reservationOutputDto.getReservationTime());
        assertEquals(1, reservationOutputDto.getTableNumber());
        assertEquals(5, reservationOutputDto.getGuests());
        assertEquals("612345678", reservationOutputDto.getPhoneNumber());

    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenUserHasNoProfile() {
        String username = "testUser";

        ReservationInputDto reservationInputDto = new ReservationInputDto();

        User mockUser = new User();
        mockUser.setUsername(username);

        when(userRepository.findById(username)).thenReturn(Optional.of(mockUser));

        when(profileRepository.findByUser(mockUser)).thenReturn(null);

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            reservationService.createReservation(reservationInputDto, username);
        });

        assertEquals("User does not have a profile, cannot create reservation", exception.getMessage());

        verify(profileRepository, times(1)).findByUser(mockUser);

        verify(reservationRepository, never()).save(any(Reservation.class));
    }


    @Test
    void shouldReturnAllReservations() {

        Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setReservationName("birthday party eren");
        reservation1.setTableNumber(1);
        reservation1.setGuests(5);
        reservation1.setPhoneNumber("0643214321");

        Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setReservationName("date eren");
        reservation2.setTableNumber(2);
        reservation2.setGuests(2);
        reservation2.setPhoneNumber("0612341234");

        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(reservationRepository.findAll()).thenReturn(reservations);

        ReservationOutputDto reservationOutputDto1 = ReservationMapper.fromModelToOutputDto(reservation1);
        ReservationOutputDto reservationOutputDto2 = ReservationMapper.fromModelToOutputDto(reservation2);


        List<ReservationOutputDto> result = reservationService.getAllReservations();

        assertEquals(2, result.size());

        assertEquals("birthday party eren", result.get(0).getReservationName());
        assertEquals(1, result.get(0).getTableNumber());
        assertEquals(5, result.get(0).getGuests());
        assertEquals("0643214321", result.get(0).getPhoneNumber());

        assertEquals("date eren", result.get(1).getReservationName());
        assertEquals(2, result.get(1).getTableNumber());
        assertEquals(2, result.get(1).getGuests());
        assertEquals("0612341234", result.get(1).getPhoneNumber());
    }


    @Test
    void shouldGetReservationById() {
        long reservationId = 1L;

        ReservationInputDto reservationInputDto = new ReservationInputDto();
        reservationInputDto.setReservationName("birthday party");
        reservationInputDto.setReservationTime(LocalDateTime.of(2024, 12, 17, 20, 0));
        reservationInputDto.setTableNumber(1);
        reservationInputDto.setGuests(5);
        reservationInputDto.setPhoneNumber("612345678");


        Reservation reservation = ReservationMapper.fromInputDtoToModel(reservationInputDto);
        reservation.setId(reservationId);

        ReservationOutputDto reservationOutputDto = ReservationMapper.fromModelToOutputDto(reservation);

        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        ReservationOutputDto outputDto = reservationService.getReservationById(reservationId);

        assertEquals(reservationId, outputDto.getId());
        assertEquals("birthday party", outputDto.getReservationName());
        assertEquals(LocalDateTime.of(2024, 12, 17, 20, 0), outputDto.getReservationTime());
        assertEquals(1, outputDto.getTableNumber());
        assertEquals(5, outputDto.getGuests());
        assertEquals("612345678", outputDto.getPhoneNumber());
    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenReservationNotFound() {
        long reservationId = 1L;

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        RecordNotFoundException exception = assertThrows(
                RecordNotFoundException.class,
                () -> reservationService.getReservationById(reservationId)
        );

        assertEquals("no reservation found with id" + reservationId, exception.getMessage());
    }

    @Test
    void shouldEditReservationById() {
        long reservationId = 1L;

        ReservationInputDto newReservation = new ReservationInputDto();
        newReservation.setReservationName("Updated Reservation");
        newReservation.setReservationTime(LocalDateTime.now());
        newReservation.setTableNumber(2);
        newReservation.setGuests(4);
        newReservation.setPhoneNumber("0612345678");

        Reservation editedReservation = new Reservation();
        editedReservation.setId(reservationId);
        editedReservation.setReservationName("Old Reservation");
        editedReservation.setReservationTime(LocalDateTime.now().minusDays(1));
        editedReservation.setTableNumber(1);
        editedReservation.setGuests(3);
        editedReservation.setPhoneNumber("0612345678");

        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(editedReservation));

        Mockito.when(reservationRepository.save(any(Reservation.class))).thenReturn(editedReservation);


        ReservationOutputDto result = reservationService.editReservationById(reservationId, newReservation);

        assertEquals("Updated Reservation", result.getReservationName());
        assertEquals(2, result.getTableNumber());
        assertEquals(4, result.getGuests());
        assertEquals("0612345678", result.getPhoneNumber());
        assertEquals(reservationId, result.getId());
    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenEditingReservationNotFound() {
        long reservationId = 1L;

        ReservationInputDto newReservation = new ReservationInputDto();
        newReservation.setReservationName("Updated Reservation");
        newReservation.setReservationTime(LocalDateTime.now());
        newReservation.setTableNumber(2);
        newReservation.setGuests(4);
        newReservation.setPhoneNumber("0612345678");

        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        RecordNotFoundException exception = assertThrows(
                RecordNotFoundException.class,
                () -> reservationService.editReservationById(reservationId, newReservation)
        );

        assertEquals("No reservation edited", exception.getMessage());
    }


    @Test
    void shouldDeleteReservationById() {
        long reservationId = 1L;
        ReservationInputDto reservationInputDto = new ReservationInputDto();
        reservationInputDto.setReservationName("birthday eren");
        reservationInputDto.setReservationTime(LocalDateTime.of(2024, 12, 17, 20, 0));
        reservationInputDto.setTableNumber(1);
        reservationInputDto.setGuests(5);
        reservationInputDto.setPhoneNumber("612345678");

        Reservation reservation = ReservationMapper.fromInputDtoToModel(reservationInputDto);

        reservation.setId(reservationId);

        Mockito.when(reservationRepository.existsById(reservationId)).thenReturn(true);
        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        reservationService.deleteReservationById(reservationId);

        verify(reservationRepository, times(1)).deleteById(reservationId);

        Reservation retrievedReservation = reservationRepository.findById(reservationId).orElse(null);

        assertEquals(reservationId, reservation.getId());
        assertEquals("birthday eren", reservation.getReservationName());
        assertEquals(LocalDateTime.of(2024, 12, 17, 20, 0), reservation.getReservationTime());
        assertEquals(1, reservation.getTableNumber());
        assertEquals(5, reservation.getGuests());
        assertEquals("612345678", reservation.getPhoneNumber());

    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenDeletingNonExistentReservation() {
        long reservationId = 1L;

        Mockito.when(reservationRepository.existsById(reservationId)).thenReturn(false);

        RecordNotFoundException exception = assertThrows(
                RecordNotFoundException.class,
                () -> reservationService.deleteReservationById(reservationId)
        );

        assertEquals("No reservation found with id" + reservationId, exception.getMessage());
    }


}
