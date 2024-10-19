package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "non_alcoholic_drinks")
public class NonAlcoholicDrink extends Drink {
    public NonAlcoholicDrink(Long id, String name, double price, String ingredients, Menu menu) {
        super(id, name, price, ingredients, menu);
    }

    public NonAlcoholicDrink() {
        super();
    }

    @Override
    public String getType() {
        return "non_alcoholic";
    }
}
