package com.example.eindopdrachtbackenderendogan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue

    private Long id;
    private String name;
    @OneToMany(mappedBy = "menu")
    private Set<Drink> drinks = new HashSet<>();

    public Menu(){

    }

    public Menu(Long id, String name, List<Drink> drinks)
    {
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

    public Set<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    public void addDrink(Drink drink) {
        this.drinks.add(drink);
    }

    public void removeDrink(Drink drink) {
        this.drinks.remove(drink);
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
