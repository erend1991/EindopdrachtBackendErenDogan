package com.example.eindopdrachtbackenderendogan.controllers;



import com.example.eindopdrachtbackenderendogan.dtos.input.DrinkInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.services.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/drink")
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
        public ResponseEntity<DrinkOutputDto> createDrink( @RequestBody DrinkInputDto drinkInputDto) {
            DrinkOutputDto dto  = drinkService.createDrink(drinkInputDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<DrinkOutputDto> editDrinkById(@PathVariable Long id, @RequestBody DrinkInputDto newDrink) {
            DrinkOutputDto dto = drinkService.editDrinkById(id, newDrink);
            return ResponseEntity.ok().body(dto);
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteDrinkById(@PathVariable long id) {

            drinkService.deleteDrinkById(id);
            return ResponseEntity.ok("drink deleted");
        }
    }


