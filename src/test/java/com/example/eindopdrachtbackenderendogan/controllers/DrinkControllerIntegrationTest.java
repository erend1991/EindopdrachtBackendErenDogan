package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.services.DrinkService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class DrinkControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DrinkService drinkService;

    DrinkInputDto drinkInputDto;
    DrinkOutputDto drinkOutputDto;

    @BeforeEach
    public void setUp() {
        drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("martini");
        drinkInputDto.setPrice(12.99);
        drinkInputDto.setIngredients("witte vermout, gin");
        drinkInputDto.setType("alcoholic");

        drinkOutputDto = new DrinkOutputDto();
        drinkOutputDto.setId(2L);
        drinkOutputDto.setName("martini");
        drinkOutputDto.setPrice(12.99);
        drinkOutputDto.setIngredients("witte vermout, gin");


        Mockito.when(drinkService.createDrink(Mockito.any(DrinkInputDto.class)))
                .thenReturn(drinkOutputDto);
    }


    @Test
    void createDrink() throws Exception {
        String requestJson = """
                {
                "name": "martini",
                "price": 12.99,
                "ingredients": "witte vermout, gin",
                "type" : "alcoholic"
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/drinks")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdId = jsonNode.get("id").asText();

        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/drinks/" + createdId));

    }
}
