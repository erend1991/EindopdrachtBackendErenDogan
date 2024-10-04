package com.example.eindopdrachtbackenderendogan.dtos.input;

import jakarta.validation.constraints.NotBlank;

public class DrinkInputDto {

    public Long id;

    @NotBlank(message = "Drink needs a name")
    public String name;
    @NotBlank(message = "Drink needs price")
    public double price;
    @NotBlank(message = "Drink needs ingredients")
    public String ingredients;
    @NotBlank(message = "is the drink alcoholic")
    public boolean alcohol;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }
}
