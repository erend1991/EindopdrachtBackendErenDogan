package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;

public class DrinkMapper {

    public static Drink fromInputDtoToModel(DrinkInputDto drinkInputDto){
        Drink d = new Drink();
        d.setName(drinkInputDto.getName());
        d.setPrice(drinkInputDto.getPrice());
        d.setIngredients(drinkInputDto.getIngredients());
        d.setAlcohol(drinkInputDto.isAlcohol());

        return d;

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
