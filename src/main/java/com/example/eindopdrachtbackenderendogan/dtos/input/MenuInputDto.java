package com.example.eindopdrachtbackenderendogan.dtos.input;

import com.example.eindopdrachtbackenderendogan.models.Drink;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class MenuInputDto {

    @NotEmpty()
    public String name;
    public List<Drink> drinks;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinkIds(List<Drink> drinkIds) {
        this.drinks = drinkIds;
    }

}
