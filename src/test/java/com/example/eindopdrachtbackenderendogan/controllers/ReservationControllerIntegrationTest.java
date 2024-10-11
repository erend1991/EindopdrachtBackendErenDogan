package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.ReservationInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ReservationOutputDto;
import com.example.eindopdrachtbackenderendogan.services.ReservationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ReservationControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ReservationService reservationService;

    ReservationInputDto reservationInputDto1;
    ReservationOutputDto reservationOutputDto1;


    @BeforeEach
    public void setUp() {
        reservationInputDto1 = new ReservationInputDto();
        reservationInputDto1.setReservationName("eren dogan");
        reservationInputDto1.setReservationTime(LocalDateTime.parse("2024-12-17T20:00:00"));
        reservationInputDto1.setTableNumber(3);
        reservationInputDto1.setGuests(2);
        reservationInputDto1.setPhoneNumber("12345678");

        reservationOutputDto1 = new ReservationOutputDto();
        reservationOutputDto1.setId(1L);
        reservationOutputDto1.setReservationName("eren dogan");
        reservationOutputDto1.setReservationTime(LocalDateTime.parse("2024-12-17T20:00:00"));
        reservationOutputDto1.setTableNumber(3);
        reservationOutputDto1.setGuests(2);
        reservationOutputDto1.setPhoneNumber("12345678");


        Mockito.when(reservationService.createReservation(Mockito.any(ReservationInputDto.class), Mockito.anyString()))
                .thenReturn(reservationOutputDto1);


    }

    @Test
    @WithMockUser(username = "testuser")
    void CreateReservation() throws Exception {

        String requestJson = """
                                
                 {
                   "reservationName": "eren dogan",
                   "reservationTime": "2024-12-17T20:00:00",
                   "tableNumber": 3,
                   "guests": 2,
                   "phoneNumber": "12345678"
                }
                 """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/reservations")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdId = jsonNode.get("id").asText();

        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/reservations/" + createdId));


    }
}

