package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.Menu;
import com.example.eindopdrachtbackenderendogan.services.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) { this.menuService = menuService; }


    @PostMapping
    public ResponseEntity<MenuOutputDto> createMenu(@RequestBody MenuInputDto menuInputDto) {
        MenuOutputDto menuOutputDto = menuService.createMenu(menuInputDto);
        return ResponseEntity.ok(menuOutputDto);
    }

    @PostMapping("/{menuId}/drink/{drinkId}")
    public ResponseEntity<String> assignDrinkToMenu(@PathVariable Long menuId, @PathVariable Long drinkId) {
        menuService.assignDrinkToMenu(menuId, drinkId);
        return ResponseEntity.ok("Drink assigned to menu");
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{menuId}/drinks")
    public ResponseEntity<List<Drink>> getDrinksByMenu(@PathVariable Long menuId) {
        List<Drink> drinks = menuService.getDrinksByMenu(menuId);
        return ResponseEntity.ok(drinks);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MenuOutputDto>deleteMenu(@PathVariable Long id){
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
