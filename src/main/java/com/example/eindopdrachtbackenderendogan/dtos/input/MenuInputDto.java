package com.example.eindopdrachtbackenderendogan.dtos.input;

import com.example.eindopdrachtbackenderendogan.models.Drink;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class MenuInputDto {

    @NotEmpty()
    public String name;

    public List<Long> drinkIds;

    public MenuInputDto(){

    }
    public MenuInputDto(String name, List<Long> drinkIds) {
        this.name = name;
        this.drinkIds = drinkIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getDrinkIds() {
        return drinkIds;
    }

    public void setDrinkIds(List<Long> drinkIds) {
        this.drinkIds = drinkIds;
    }

}
