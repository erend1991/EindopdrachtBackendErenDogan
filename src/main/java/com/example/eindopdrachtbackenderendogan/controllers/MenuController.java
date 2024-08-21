package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
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
    @GetMapping
    public ResponseEntity<MenuOutputDto>getMenu(){
        MenuOutputDto menuOutputDto = menuService.getMenu();
        return ResponseEntity.ok(menuOutputDto);
    }

    @DeleteMapping
    public ResponseEntity<String>deleteMenu(){
        menuService.deleteMenu();
        return ResponseEntity.noContent().build();
    }
}
