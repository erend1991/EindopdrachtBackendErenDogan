package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "drinks")
public abstract class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Drink needs a name")
    private String name;
    @NotNull(message = "Drink needs price")
    private Double price;
    @NotBlank(message = "Drink needs ingredients")
    private String ingredients;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    Menu menu;


    public Drink(Long id, String name, Double price, String ingredients, Menu menu) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public abstract String getType();



    public void setMenu(Menu menu) {
        if(this.menu != null){
            this.menu.getDrinks().remove(this);
        }
        this.menu = menu;
        if (menu != null){
            menu.getDrinks().add(this);
        }
    }
}
