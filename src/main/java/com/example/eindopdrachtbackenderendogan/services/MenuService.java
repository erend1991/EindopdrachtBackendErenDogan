package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.MenuMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.Menu;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import com.example.eindopdrachtbackenderendogan.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final DrinkRepository drinkRepository;

    public MenuService(MenuRepository menuRepository, DrinkRepository drinkRepository){
        this.menuRepository = menuRepository;
        this.drinkRepository = drinkRepository;
    }

    public MenuOutputDto createMenu(MenuInputDto menuInputDto) {
        Menu menu = menuRepository.findAll().stream().findFirst().orElse(new Menu());
        menu.setName(menuInputDto.getName());
        menu.setDrinks (menuInputDto.getDrinks());

        Menu savedMenu = menuRepository.save(menu);

        return MenuMapper.fromModelToOutputDto(savedMenu);
    }
    public MenuOutputDto getMenu() {
        Menu menu = menuRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("no Menu found"));

        return MenuMapper.fromModelToOutputDto(menu);
    }
    public void deleteMenu(){
        menuRepository.deleteAll();
    }
}
