package com.example.eindopdrachtbackenderendogan.controllers;


import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.BadRequestException;
import com.example.eindopdrachtbackenderendogan.services.DrinkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<List<DrinkOutputDto>> getAllDrinks() {
        drinkService.getAllDrinks();

        return ResponseEntity.ok().body(drinkService.getAllDrinks());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DrinkOutputDto> getDrink(@PathVariable long id) {
        return ResponseEntity.ok().body(drinkService.getDrinkById(id));
    }


    @PostMapping
    public ResponseEntity<DrinkOutputDto> createDrink(@Valid @RequestBody DrinkInputDto drinkInputDto) {
        if (!"alcoholic".equalsIgnoreCase(drinkInputDto.getType()) &&
                !"non-alcoholic".equalsIgnoreCase(drinkInputDto.getType())) {
            throw new BadRequestException("Invalid drink type. Must be 'alcoholic' or 'non-alcoholic'.");
        }
        DrinkOutputDto dto = drinkService.createDrink(drinkInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrinkOutputDto> editDrinkById(@Valid @PathVariable Long id, @RequestBody DrinkInputDto newDrink) {
        DrinkOutputDto dto = drinkService.editDrinkById(id, newDrink);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrinkById(@PathVariable long id) {

        drinkService.deleteDrinkById(id);
        return ResponseEntity.ok("drink deleted with id" + id);
    }

    @GetMapping("/alcoholic")
    public ResponseEntity<List<DrinkOutputDto>> getAllAlcoholicDrinks() {
        List<DrinkOutputDto> alcoholicDrinks = drinkService.getAllAlcoholicDrinks();
        return ResponseEntity.ok(alcoholicDrinks);
    }

    @GetMapping("/non-alcoholic")
    public ResponseEntity<List<DrinkOutputDto>> getAllNonAlcoholicDrinks() {
        List<DrinkOutputDto> nonAlcoholicDrinks = drinkService.getAllNonAlcoholicDrinks();
        return ResponseEntity.ok(nonAlcoholicDrinks);
    }

}


