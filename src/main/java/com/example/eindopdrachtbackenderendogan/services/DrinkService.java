package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.DrinkMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
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
            throw new RecordNotFoundException("no drink found with id " + id);
        }

    }

    public  DrinkOutputDto createDrink (DrinkInputDto drinkInputDto){
        Drink d = drinkRepository.save((DrinkMapper.fromInpuDtoToModel(drinkInputDto)));
        return DrinkMapper.fromModelToOutputDto(d);
    }

    public  DrinkOutputDto editDrinkById(long id, DrinkInputDto newDrink){
        Optional<Drink> d = drinkRepository.findById(id);

        if (d.isPresent()){
            Drink drink = d.get();
            drink.setName(newDrink.name);
            drink.setPrice(newDrink.price);
            drink.setIngredients(newDrink.ingredients);
            drink.setAlcohol(newDrink.alcohol);
            drinkRepository.save(drink);

            return DrinkMapper.fromModelToOutputDto(drink);

        }else {
            throw new RecordNotFoundException("no drink edited");
        }
    }

    public void deleteDrinkById(long id) {
        if (drinkRepository.existsById(id)) {
            drinkRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("no drink found with this id");
        }

    }
}
