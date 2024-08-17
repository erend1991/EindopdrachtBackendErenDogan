package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;

public class DrinkMapper {

    public static Drink fromInpuDtoToModel(DrinkInputDto drinkInputDto){
        Drink d = new Drink();
        d.setName(drinkInputDto.getName());
        d.setPrice(drinkInputDto.getPrice());
        d.setIngredients(drinkInputDto.getIngredients());
        d.setAlcohol(drinkInputDto.isAlcohol());

        return d;

    }

    public static DrinkOutputDto fromModelToOutpuDto(Drink drink){
        DrinkOutputDto drinkOutputDto = new DrinkOutputDto();

        drinkOutputDto.setId(drink.getId());
        drinkOutputDto.setName(drink.getName());
        drinkOutputDto.setPrice(drink.getPrice());
        drinkOutputDto.setIngredients(drinkOutputDto.getIngredients());
        drinkOutputDto.setAlcohol(drinkOutputDto.isAlcohol());


        return drinkOutputDto;
    }
}
