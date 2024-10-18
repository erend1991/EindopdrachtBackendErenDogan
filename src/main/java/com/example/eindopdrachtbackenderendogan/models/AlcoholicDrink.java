package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "alcoholic_drinks")
public class AlcoholicDrink extends Drink {

        public AlcoholicDrink(Long id, String name, double price, String ingredients, Menu menu) {
            super(id, name, price, ingredients, menu);
        }

        public AlcoholicDrink() {}

    @Override
    public String getType() {
        return "alcoholic";
    }
    }

