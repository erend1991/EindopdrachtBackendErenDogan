package com.example.eindopdrachtbackenderendogan.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class DrinkControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void createDrink() throws Exception {
        String requestJson = """
                {
                "name": "martini",
                "price": 12.0,
                "ingredients": "witte vermout, gin",
                "alcohol": true
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/drink")
                .contentType((MediaType) APPLICATION_JSON)
                .content(requestJson))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

    }
}