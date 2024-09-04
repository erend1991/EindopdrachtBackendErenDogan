package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.controllers.DrinkController;
import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.DrinkMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DrinkServiceTest {

    Drink drink;

    @Mock
    DrinkRepository drinkRepository;

    @InjectMocks
    DrinkService drinkService;


    @Test
    @DisplayName("create new drink")
    void createDrink() {

        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setId(1L);
        drinkInputDto.setName("martini");
        drinkInputDto.setPrice(12);
        drinkInputDto.setIngredients("witte vermout, gin");
        drinkInputDto.setAlcohol(true);

        Mockito.when(drinkRepository.save(Mockito.any())).thenReturn(DrinkMapper.fromInputDtoToModel(drinkInputDto));

        DrinkOutputDto drinkOutputDto =drinkService.createDrink(drinkInputDto);

        assertEquals("martini", drinkOutputDto.getName());
        assertEquals(12, drinkOutputDto.getPrice());
        assertEquals("witte vermout, gin", drinkOutputDto.getIngredients());
        assertTrue(drinkInputDto.isAlcohol());


    }
}