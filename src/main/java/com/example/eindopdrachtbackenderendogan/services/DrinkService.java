package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.DrinkMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.DrinkCreateException;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.AlcoholicDrink;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.NonAlcoholicDrink;
import com.example.eindopdrachtbackenderendogan.repositories.AlcoholicDrinkRepository;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import com.example.eindopdrachtbackenderendogan.repositories.NonAlcoholicDrinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final AlcoholicDrinkRepository alcoholicDrinkRepository;
    private final NonAlcoholicDrinkRepository nonAlcoholicDrinkRepository;

    public DrinkService(DrinkRepository drinkRepository, AlcoholicDrinkRepository alcoholicDrinkRepository, NonAlcoholicDrinkRepository nonAlcoholicDrinkRepository) {
        this.drinkRepository = drinkRepository;
        this.alcoholicDrinkRepository = alcoholicDrinkRepository;
        this.nonAlcoholicDrinkRepository = nonAlcoholicDrinkRepository;
    }


    public DrinkOutputDto createDrink(DrinkInputDto drinkInputDto) {
        try {
            Drink drink = DrinkMapper.fromInputDtoToModel(drinkInputDto);

            Drink savedDrink = drinkRepository.save(drink);

            return DrinkMapper.fromModelToOutputDto(savedDrink);
        } catch (Exception e) {
            throw new DrinkCreateException("Failed to create drink: " + e.getMessage());
        }
    }


    public List<DrinkOutputDto> getAllDrinks() {
        List<Drink> allDrinks = drinkRepository.findAll();
        List<DrinkOutputDto> allDrinksOutput = new ArrayList<>();

        for (Drink d : allDrinks) {
            allDrinksOutput.add(DrinkMapper.fromModelToOutputDto(d));
        }
        return allDrinksOutput;
    }

    public DrinkOutputDto getDrinkById(long id) {
        Optional<Drink> d = drinkRepository.findById(id);
        if (d.isPresent()) {
            return DrinkMapper.fromModelToOutputDto(d.get());

        } else {
            throw new IndexOutOfBoundsException("no drink found with id " + id);
        }

    }


    public DrinkOutputDto editDrinkById(long id, DrinkInputDto newDrink) {
        Optional<Drink> d = drinkRepository.findById(id);

        if (d.isPresent()) {
            Drink drink = d.get();
            drink.setName(newDrink.name);
            drink.setPrice(newDrink.price);
            drink.setIngredients(newDrink.ingredients);
            drinkRepository.save(drink);

            return DrinkMapper.fromModelToOutputDto(drink);

        } else {
            throw new RecordNotFoundException("no drink edited");
        }
    }

    public void deleteDrinkById(long id) {
        if (drinkRepository.existsById(id)) {
            drinkRepository.deleteById(id);
        } else {
            throw new IndexOutOfBoundsException("no drink found with this id");
        }

    }

    public List<DrinkOutputDto> getAllAlcoholicDrinks() {
        List<DrinkOutputDto> alcoholicDrinks = drinkRepository.findAll().stream()
                .filter(drink -> drink instanceof AlcoholicDrink)
                .map(DrinkMapper::fromModelToOutputDto)
                .collect(Collectors.toList());

        if (alcoholicDrinks.isEmpty()) {
            throw new RecordNotFoundException("No alcoholic drinks found.");
        }
        return alcoholicDrinks;

    }

    public List<DrinkOutputDto> getAllNonAlcoholicDrinks() {
        List<DrinkOutputDto> nonAlcoholicDrinks = drinkRepository.findAll().stream()
                .filter(drink -> drink instanceof NonAlcoholicDrink)
                .map(DrinkMapper::fromModelToOutputDto)
                .collect(Collectors.toList());

        if (nonAlcoholicDrinks.isEmpty()) {
            throw new RecordNotFoundException("No non-alcoholic drinks found.");
        }

        return nonAlcoholicDrinks;
    }


}

