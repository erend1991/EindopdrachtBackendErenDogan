package com.example.eindopdrachtbackenderendogan.dtos.input;

import com.example.eindopdrachtbackenderendogan.models.Drink;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class MenuInputDto {

    public String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
