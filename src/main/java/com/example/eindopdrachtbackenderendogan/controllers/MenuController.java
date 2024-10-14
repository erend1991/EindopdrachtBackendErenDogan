package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.Menu;
import com.example.eindopdrachtbackenderendogan.services.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) { this.menuService = menuService; }


    @PostMapping
    public ResponseEntity<MenuOutputDto> createMenu(@Valid @RequestBody MenuInputDto menuInputDto) {
        MenuOutputDto menuOutputDto = menuService.createMenu(menuInputDto);
        return ResponseEntity.ok(menuOutputDto);
    }

    @PostMapping("/{menuId}/drinks/{drinkId}")

    public ResponseEntity<String> assignDrinkToMenu(@Valid @PathVariable Long menuId, @PathVariable Long drinkId) {

        menuService.assignDrinkToMenu(menuId, drinkId);
        return ResponseEntity.ok("Drink assigned to menu");
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<List<Drink>> getMenuById(@PathVariable Long id, @PathVariable String Id) {
        List<Drink> drinks = menuService.getMenuById(id);
        return ResponseEntity.ok(drinks);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteMenu(@PathVariable Long id){
        menuService.deleteMenu(id);
        return ResponseEntity.ok("menu deleted with id" + id );
    }
}
