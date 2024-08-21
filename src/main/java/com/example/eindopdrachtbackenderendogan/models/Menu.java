package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue

    private Long id;
    private String name;
    @OneToMany(mappedBy = "menu")
    private List<Drink> drinks = new ArrayList<>();

    public Menu(){

    }

    public Menu(Long id, String name, List<Drink> drinks)
    {
        this.id = id;
        this.name = name;
        this.drinks = drinks;
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
        drinks.add(drink);
        drink.setMenu(this);
    }

    public void removeDrink(Drink drink) {
        drinks.remove(drink);
        drink.setMenu(null);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("menu:").append(name).append(" ");
        sb.append("drinks:");
        for (Drink drink : drinks){
            sb.append(drink).append(" ");
        }
        return sb.toString();
    }
}
