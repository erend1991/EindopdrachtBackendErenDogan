package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.DrinkMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DrinkServiceUnitTest {

    Drink drink;

    @Mock
    DrinkRepository drinkRepository;

    @InjectMocks
    DrinkService drinkService;


    @Test
    void shouldCreateDrink() {

        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setId(1L);
        drinkInputDto.setName("Martini");
        drinkInputDto.setPrice(12);
        drinkInputDto.setIngredients("Witte vermout, Gin");
        drinkInputDto.setAlcohol(true);

        when(drinkRepository.save(any())).thenReturn(DrinkMapper.fromInputDtoToModel(drinkInputDto));

        DrinkOutputDto drinkOutputDto = drinkService.createDrink(drinkInputDto);

        assertEquals("Martini", drinkOutputDto.getName());
        assertEquals(12, drinkOutputDto.getPrice());
        assertEquals("Witte vermout, Gin", drinkOutputDto.getIngredients());
        assertTrue(drinkInputDto.isAlcohol());


    }

    @Test
    void shouldGetDrinkById() {
        long drinkId = 1L;
        Drink drink = new Drink();
        drink.setId(drinkId);
        drink.setName("Martini");
        drink.setPrice(12);
        drink.setIngredients("Witte vermout, Gin");
        drink.setAlcohol(true);

        DrinkOutputDto expectedOutputDto = DrinkMapper.fromModelToOutputDto(drink);

        when(drinkRepository.findById(anyLong())).thenReturn(Optional.of(drink));

        DrinkOutputDto drinkOutputDto = drinkService.getDrinkById(drinkId);

        assertEquals(1L, drinkOutputDto.getId());
        assertEquals("Martini", drinkOutputDto.getName());
        assertEquals(12, drinkOutputDto.getPrice());
        assertEquals("Witte vermout, Gin", drinkOutputDto.getIngredients());
        assertEquals(true, drinkOutputDto.isAlcohol());
    }
    @Test
    void shouldThrowRecordNotFoundExceptionWhenGettingNonExistentDrink() {
        long drinkId = 1L;

        when(drinkRepository.findById(drinkId)).thenReturn(Optional.empty());

        RecordNotFoundException exception = assertThrows(
                RecordNotFoundException.class,
                () -> drinkService.getDrinkById(drinkId)
        );

        assertEquals("no drink found with id " + drinkId, exception.getMessage());
    }


    @Test
    void shouldGetAllDrinks() {
        Drink drink1 = new Drink();
        drink1.setId(1L);
        drink1.setName("Martini");
        drink1.setPrice(12);
        drink1.setIngredients("Witte vermout, Gin");
        drink1.setAlcohol(true);

        Drink drink2 = new Drink();
        drink2.setId(2L);
        drink2.setName("Mojito");
        drink2.setPrice(12);
        drink2.setIngredients("Rum, Mint, Suiker");
        drink2.setAlcohol(true);

        List<Drink> drinks = Arrays.asList(drink1, drink2);
        when(drinkRepository.findAll()).thenReturn(drinks);

        DrinkOutputDto drinkOutputDto1 = DrinkMapper.fromModelToOutputDto(drink1);
        DrinkOutputDto drinkOutputDto2 = DrinkMapper.fromModelToOutputDto(drink2);


        List<DrinkOutputDto> result = drinkService.getAllDrinks();

        assertEquals(2, result.size());

        assertEquals(1L, drinkOutputDto1.getId());
        assertEquals("Martini", drinkOutputDto1.getName());
        assertEquals(12, drinkOutputDto1.getPrice());
        assertEquals("Witte vermout, Gin", drinkOutputDto1.getIngredients());
        assertEquals(true, drinkOutputDto1.isAlcohol());

        assertEquals(2L, drinkOutputDto2.getId());
        assertEquals("Mojito", drinkOutputDto2.getName());
        assertEquals(12, drinkOutputDto2.getPrice());
        assertEquals("Rum, Mint, Suiker", drinkOutputDto2.getIngredients());
        assertEquals(true, drinkOutputDto2.isAlcohol());
    }

    @Test
    void shouldEditDrinkById() {
        Long drinkId = 1L;

        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("Martini");
        drinkInputDto.setPrice(15);
        drinkInputDto.setIngredients("Witte vermout, Gin, Citroen");
        drinkInputDto.setAlcohol(true);

        Drink editedDrink = new Drink();
        editedDrink.setId(drinkId);
        editedDrink.setName("Martini");
        editedDrink.setPrice(12);
        editedDrink.setIngredients("Witte vermout, Gin");
        editedDrink.setAlcohol(true);

        Drink updatedDrink = new Drink();
        updatedDrink.setId(drinkId);
        updatedDrink.setName("Martini");
        updatedDrink.setPrice(15);
        updatedDrink.setIngredients("Witte vermout, Gin, Citroen");
        updatedDrink.setAlcohol(true);

        DrinkOutputDto OutputDto = DrinkMapper.fromModelToOutputDto(updatedDrink);

        Mockito.when(drinkRepository.findById(anyLong())).thenReturn(Optional.of(editedDrink));
        Mockito.when(drinkRepository.save(any(Drink.class))).thenReturn(updatedDrink);

        DrinkOutputDto drinkOutputDto = drinkService.editDrinkById(drinkId, drinkInputDto);

        assertEquals("Martini", drinkOutputDto.getName());
        assertEquals(15, drinkOutputDto.getPrice());
        assertEquals("Witte vermout, Gin, Citroen", drinkOutputDto.getIngredients());
        assertTrue(drinkOutputDto.isAlcohol());


    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenDrinkNotFound() {
        long nonExistentDrinkId = 10L;
        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("Martini");
        drinkInputDto.setPrice(15);
        drinkInputDto.setIngredients("Witte vermout, Gin, Citroen");
        drinkInputDto.setAlcohol(true);

        Mockito.when(drinkRepository.findById(anyLong())).thenReturn(Optional.empty());

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            drinkService.editDrinkById(nonExistentDrinkId, drinkInputDto);
        });

        assertEquals("no drink edited", exception.getMessage());
    }


    @Test
    void shouldDeleteDrinkById() {
        long id = 1L;

        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("Martini");
        drinkInputDto.setPrice(15);
        drinkInputDto.setIngredients("Witte vermout, Gin, Citroen");
        drinkInputDto.setAlcohol(true);

        Mockito.when(drinkRepository.existsById(id)).thenReturn(true);

        drinkService.deleteDrinkById(id);

        Mockito.verify(drinkRepository).deleteById(id);


        assertEquals(true, drinkRepository.existsById(id), "should return true for id: " + id);

        Mockito.verify(drinkRepository, Mockito.times(1)).deleteById(id);

    }
    @Test
    void shouldThrowRecordNotFoundExceptionWhenDrinkToDeleteNotFound() {
        long nonExistentDrinkId = 10L;

        Mockito.when(drinkRepository.existsById(anyLong())).thenReturn(false);

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            drinkService.deleteDrinkById(nonExistentDrinkId);
        });

        assertEquals("no drink found with this id", exception.getMessage());
    }
}


