package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;

public class DrinkMapper {

    public static Drink fromInputDtoToModel(DrinkInputDto drinkInputDto){
        Drink drink = new Drink();
        drink.setName(drinkInputDto.getName());
        drink.setPrice(drinkInputDto.getPrice());
        drink.setIngredients(drinkInputDto.getIngredients());
        drink.setAlcohol(drinkInputDto.isAlcohol());

        return drink;

    }

    public static DrinkOutputDto fromModelToOutputDto(Drink drink){
        DrinkOutputDto drinkOutputDto = new DrinkOutputDto();

        drinkOutputDto.setId(drink.getId());
        drinkOutputDto.setName(drink.getName());
        drinkOutputDto.setPrice(drink.getPrice());
        drinkOutputDto.setIngredients(drink.getIngredients());
        drinkOutputDto.setAlcohol(drink.isAlcohol());


        return drinkOutputDto;
    }
}
