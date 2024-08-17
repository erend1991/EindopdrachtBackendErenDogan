package com.example.eindopdrachtbackenderendogan.dtos.output;

public class DrinkOutputDto {

    private Long id;

    private String name;
    private double price;
    private String ingredients;

    private boolean alcohol;

    public DrinkOutputDto(){

    }

    public DrinkOutputDto(Long id, String name, double price, String ingredients, boolean alcohol) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.alcohol = alcohol;
    }

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
