package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "menu needs a name")
    private String name;
    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Drink> drinks = new ArrayList<>();

    public Menu() {

    }

    public Menu(Long id, String name, List<Drink> drinks) {
        this.id = id;
        this.name = name;

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

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public void addDrink(Drink drink) {
        this.drinks.add(drink);
        drink.setMenu(this);
    }

    public void removeDrink(Drink drink) {
        this.drinks.remove(drink);
        drink.setMenu(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("menu:").append(name).append(" ");
        sb.append("drinks:");
        for (Drink drink : drinks) {
            sb.append(drink).append(" ");
        }
        return sb.toString();
    }
}
