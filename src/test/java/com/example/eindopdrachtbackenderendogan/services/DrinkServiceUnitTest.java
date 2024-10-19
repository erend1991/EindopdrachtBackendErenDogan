package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.DrinkMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.DrinkCreateException;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.AlcoholicDrink;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.NonAlcoholicDrink;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

        when(drinkRepository.save(any())).thenReturn(DrinkMapper.fromInputDtoToModel(drinkInputDto));

        DrinkOutputDto drinkOutputDto = drinkService.createDrink(drinkInputDto);

        assertEquals("Martini", drinkOutputDto.getName());
        assertEquals(12, drinkOutputDto.getPrice());
        assertEquals("Witte vermout, Gin", drinkOutputDto.getIngredients());


    }

    @Test
    void ShouldThrowCreateDrinkThrowsExceptionFailed() {
        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("Test Drink");

        when(drinkRepository.save(any(Drink.class))).thenThrow(new RuntimeException());

        DrinkCreateException thrown = assertThrows
                (DrinkCreateException.class,
                        () -> drinkService.createDrink(drinkInputDto),
                "Expected createDrink() to throw DrinkCreateException, but it didn't"
        );

        assertEquals("Failed to create drink: null", thrown.getMessage());
    }


    @Test
    void shouldGetDrinkById() {
        long drinkId = 1L;
        Drink drink = new AlcoholicDrink();
        drink.setId(drinkId);
        drink.setName("Martini");
        drink.setPrice(12);
        drink.setIngredients("Witte vermout, Gin");

        DrinkOutputDto expectedDrinkOutputDto = DrinkMapper.fromModelToOutputDto(drink);

        Mockito.when(drinkRepository.findById(anyLong())).thenReturn(Optional.of(drink));

        DrinkOutputDto drinkOutputDto = drinkService.getDrinkById(drinkId);

        assertEquals(1L, drinkOutputDto.getId());
        assertEquals("Martini", drinkOutputDto.getName());
        assertEquals(12, drinkOutputDto.getPrice());
        assertEquals("Witte vermout, Gin", drinkOutputDto.getIngredients());
    }
    @Test
    void shouldThrowRecordNotFoundExceptionWhenGettingNonExistentDrink() {
        long drinkId = 1L;

        when(drinkRepository.findById(drinkId)).thenReturn(Optional.empty());

        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> drinkService.getDrinkById(drinkId)
        );

        assertEquals("no drink found with id " + drinkId, exception.getMessage());
    }


    @Test
    void shouldGetAllDrinks() {
        Drink drink1 = new AlcoholicDrink();
        drink1.setId(1L);
        drink1.setName("Martini");
        drink1.setPrice(12);
        drink1.setIngredients("Witte vermout, Gin");

        Drink drink2 = new AlcoholicDrink();
        drink2.setId(2L);
        drink2.setName("Mojito");
        drink2.setPrice(12);
        drink2.setIngredients("Rum, Mint, Suiker");

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

        assertEquals(2L, drinkOutputDto2.getId());
        assertEquals("Mojito", drinkOutputDto2.getName());
        assertEquals(12, drinkOutputDto2.getPrice());
        assertEquals("Rum, Mint, Suiker", drinkOutputDto2.getIngredients());
    }

    @Test
    void shouldEditDrinkById() {
        Long drinkId = 1L;

        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("Martini");
        drinkInputDto.setPrice(15);
        drinkInputDto.setIngredients("Witte vermout, Gin, Citroen");

        Drink editedDrink = new AlcoholicDrink();
        editedDrink.setId(drinkId);
        editedDrink.setName("Martini");
        editedDrink.setPrice(12);
        editedDrink.setIngredients("Witte vermout, Gin");

        Drink updatedDrink = new AlcoholicDrink();
        updatedDrink.setId(drinkId);
        updatedDrink.setName("Martini");
        updatedDrink.setPrice(15);
        updatedDrink.setIngredients("Witte vermout, Gin, Citroen");

        DrinkOutputDto OutputDto = DrinkMapper.fromModelToOutputDto(updatedDrink);

        Mockito.when(drinkRepository.findById(anyLong())).thenReturn(Optional.of(editedDrink));
        Mockito.when(drinkRepository.save(any(Drink.class))).thenReturn(updatedDrink);

        DrinkOutputDto drinkOutputDto = drinkService.editDrinkById(drinkId, drinkInputDto);

        assertEquals("Martini", drinkOutputDto.getName());
        assertEquals(15, drinkOutputDto.getPrice());
        assertEquals("Witte vermout, Gin, Citroen", drinkOutputDto.getIngredients());


    }

    @Test
    void shouldThrowRecordNotFoundExceptionWhenDrinkNotFound() {
        long nonExistentDrinkId = 10L;
        DrinkInputDto drinkInputDto = new DrinkInputDto();
        drinkInputDto.setName("Martini");
        drinkInputDto.setPrice(15);
        drinkInputDto.setIngredients("Witte vermout, Gin, Citroen");

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

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            drinkService.deleteDrinkById(nonExistentDrinkId);
        });

        assertEquals("no drink found with this id", exception.getMessage());
    }

    @Test
    void shouldGetAllAlcoholicDrinks() {
        Drink drink1 = new AlcoholicDrink();
        drink1.setId(1L);
        drink1.setName("ammeretosour");
        drink1.setPrice(12.00);
        drink1.setIngredients("ammereto, citroenzuur");

        Drink drink2 = new AlcoholicDrink();
        drink2.setId(2L);
        drink2.setName("wodka");
        drink2.setPrice(8.00);
        drink2.setIngredients("absolute");

        List<Drink> drinks = Arrays.asList(drink1, drink2);
        when(drinkRepository.findAll()).thenReturn(drinks);

        DrinkOutputDto drinkOutputDto1 = DrinkMapper.fromModelToOutputDto(drink1);
        DrinkOutputDto drinkOutputDto2 = DrinkMapper.fromModelToOutputDto(drink2);

        List<DrinkOutputDto> result = drinkService.getAllAlcoholicDrinks();

        assertEquals(2, result.size());

        assertEquals(1L, drinkOutputDto1.getId());
        assertEquals("ammeretosour", drinkOutputDto1.getName());
        assertEquals(12.00, drinkOutputDto1.getPrice());
        assertEquals("ammereto, citroenzuur", drinkOutputDto1.getIngredients());

        assertEquals(2L, drinkOutputDto2.getId());
        assertEquals("wodka", drinkOutputDto2.getName());
        assertEquals(8, drinkOutputDto2.getPrice());
        assertEquals("absolute", drinkOutputDto2.getIngredients());
    }
    @Test
    void shouldThrowExceptionWhenNoAlcoholicDrinksFound() {
        Mockito.when(drinkRepository.findAll()).thenReturn(Collections.emptyList());

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            drinkService.getAllAlcoholicDrinks();
        });

        assertEquals("No alcoholic drinks found.", exception.getMessage());
    }



    @Test
    void shouldGetAllNonAlcoholicDrinks() {
        Drink drink1 = new NonAlcoholicDrink();
        drink1.setId(1L);
        drink1.setName("cola");
        drink1.setPrice(3);
        drink1.setIngredients("coca cola");

        Drink drink2 = new NonAlcoholicDrink();
        drink2.setId(2L);
        drink2.setName("fanta");
        drink2.setPrice(3);
        drink2.setIngredients("fanta");

        List<Drink> drinks = Arrays.asList(drink1, drink2);
        when(drinkRepository.findAll()).thenReturn(drinks);

        DrinkOutputDto drinkOutputDto1 = DrinkMapper.fromModelToOutputDto(drink1);
        DrinkOutputDto drinkOutputDto2 = DrinkMapper.fromModelToOutputDto(drink2);

        List<DrinkOutputDto> result = drinkService.getAllNonAlcoholicDrinks();

        assertEquals(2, result.size());

        assertEquals(1L, drinkOutputDto1.getId());
        assertEquals("cola", drinkOutputDto1.getName());
        assertEquals(3, drinkOutputDto1.getPrice());
        assertEquals("coca cola", drinkOutputDto1.getIngredients());

        assertEquals(2L, drinkOutputDto2.getId());
        assertEquals("fanta", drinkOutputDto2.getName());
        assertEquals(3, drinkOutputDto2.getPrice());
        assertEquals("fanta", drinkOutputDto2.getIngredients());
    }
    @Test
    void shouldThrowExceptionWhenNoNonAlcoholicDrinksFound() {
        Mockito.when(drinkRepository.findAll()).thenReturn(Collections.emptyList());

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            drinkService.getAllNonAlcoholicDrinks();
        });

        assertEquals("No non-alcoholic drinks found.", exception.getMessage());
    }

}



