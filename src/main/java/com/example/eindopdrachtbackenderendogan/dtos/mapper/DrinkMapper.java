package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.models.AlcoholicDrink;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.NonAlcoholicDrink;

public class DrinkMapper {

    public static Drink fromInputDtoToModel(DrinkInputDto drinkInputDto) {
        Drink drink;

        if ("alcoholic".equalsIgnoreCase(drinkInputDto.getType())) {
            drink = new AlcoholicDrink();
        } else {
            drink = new NonAlcoholicDrink();
        }

        drink.setName(drinkInputDto.getName());
        drink.setPrice(drinkInputDto.getPrice());
        drink.setIngredients(drinkInputDto.getIngredients());


        return drink;

    }

    public static DrinkOutputDto fromModelToOutputDto(Drink drink) {
        DrinkOutputDto drinkOutputDto = new DrinkOutputDto();

        drinkOutputDto.setId(drink.getId());
        drinkOutputDto.setName(drink.getName());
        drinkOutputDto.setPrice(drink.getPrice());
        drinkOutputDto.setIngredients(drink.getIngredients());

        if (drink instanceof AlcoholicDrink) {
            drinkOutputDto.setType("alcoholic");
        } else {
            drinkOutputDto.setType("non-alcoholic");
        }


        return drinkOutputDto;
    }
}
