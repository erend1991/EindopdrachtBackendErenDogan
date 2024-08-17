package com.example.eindopdrachtbackenderendogan.dtos.output;

import java.util.List;

public class MenuOutputDto {

    private Long id;
    private String name;
    private List<DrinkOutputDto> drinks;

    public MenuOutputDto(){

    }

    public MenuOutputDto(Long id, String name, List<DrinkOutputDto> drinks) {
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

    public List<DrinkOutputDto> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkOutputDto> drinks) {
        this.drinks = drinks;
    }
}
