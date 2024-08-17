package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.*;

@Entity
public class Drink {

    @Id
    @GeneratedValue

    Long id;
    private String name;
    private double price;
    private String ingredients;
    private boolean alcohol;

    @ManyToOne
    @MapsId("drinkId")
    @JoinColumn(name = "menu_id")
    private Menu menu;


    public Drink(Long id, String name, double price, String ingredients, boolean alcohol, Menu menu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.menu = menu;
    }

    public Drink() {

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


}
