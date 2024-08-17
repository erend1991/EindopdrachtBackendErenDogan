package com.example.eindopdrachtbackenderendogan.dtos.input;

public class DrinkInputDto {

    public Long id;

    public String name;
    public double price;
    public String ingredients;

    public boolean alcohol;


//    public DrinkInputDto(Long id, String name, double price, String ingredients, boolean alcohol) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.ingredients = ingredients;
//        this.alcohol = alcohol;
//    }




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
