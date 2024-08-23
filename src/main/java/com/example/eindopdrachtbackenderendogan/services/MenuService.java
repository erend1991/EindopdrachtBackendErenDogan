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

        Menu menu = MenuMapper.fromInputDtoToModel(menuInputDto);

        menuRepository.save(menu);

         return MenuMapper.fromModelToOutputDto(menu);

    }
    public MenuOutputDto getMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Menu found with id " + id));
        return MenuMapper.fromModelToOutputDto(menu);
    }

    public void deleteMenu(Long id){
        Menu menu= menuRepository.findById(id).orElseThrow(() -> new RuntimeException("no menu found with id" + id ));
        menuRepository.delete(menu);
    }

    public void assignDrinkToMenu(Long menuId, Long drinkId){
        Menu menu = menuRepository.findById(menuId).orElseThrow(() ->new RuntimeException("no menu found with id" + menuId));
        Drink drink = drinkRepository.findById(drinkId).orElseThrow(() -> new RuntimeException("no drink found with id"+ drinkId));

        menu.addDrink(drink);
        menuRepository.save(menu);
    }
}
