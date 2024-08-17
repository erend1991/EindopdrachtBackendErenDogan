package com.example.eindopdrachtbackenderendogan.dtos.input;

import com.example.eindopdrachtbackenderendogan.models.Drink;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class MenuInputDto {

    public Long id;
    @NotEmpty()
    public String name;

    public List<Drink> drinks;
}
