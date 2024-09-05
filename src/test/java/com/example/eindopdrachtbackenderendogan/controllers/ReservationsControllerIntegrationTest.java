package com.example.eindopdrachtbackenderendogan.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@WebMvcTest(ReservationsController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class ReservationsControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void CreateReservation() throws Exception {

        String requestJson = """
                                
                 {
                   "reservationName": "eren dogan",
                   "reservationTime": "2024-17-12T20:00:00",
                   "tableNumber": 3,
                   "guests": 2,
                   "phoneNumber": 12345678
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

