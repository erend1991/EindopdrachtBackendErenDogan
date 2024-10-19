package com.example.eindopdrachtbackenderendogan.dtos.input;

import jakarta.validation.constraints.NotEmpty;


public class MenuInputDto {

    @NotEmpty(message = "menu needs a name")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
