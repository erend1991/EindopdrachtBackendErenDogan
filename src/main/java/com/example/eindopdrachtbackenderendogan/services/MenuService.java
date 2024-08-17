package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.MenuMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.Menu;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import com.example.eindopdrachtbackenderendogan.repositories.MenuRepository;

import java.util.List;

public class MenuService {

    private final MenuRepository menuRepository;
    private final DrinkRepository drinkRepository;

    public MenuService(MenuRepository menuRepository, DrinkRepository drinkRepository){
        this.menuRepository = menuRepository;
        this.drinkRepository = drinkRepository;
    }

    public MenuOutputDto createMenu(MenuInputDto menuInputDto) {
        List<Drink> drinks = drinkRepository.findAllById(menuInputDto.getDrinkIds());

        Menu menu = MenuMapper.fromInputDtoToModel(menuInputDto, drinks);

        menu.setDrinks(drinks);

        for (Drink drink : drinks) {
            drink.setMenu(menu);
        }

        menu = menuRepository.save(menu);


        return MenuMapper.fromModelToOutputDto(menu);
    }
}
