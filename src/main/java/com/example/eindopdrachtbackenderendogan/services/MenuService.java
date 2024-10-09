package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.MenuMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.MenuAlreadyExistsException;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.Menu;
import com.example.eindopdrachtbackenderendogan.repositories.DrinkRepository;
import com.example.eindopdrachtbackenderendogan.repositories.MenuRepository;
import org.springframework.stereotype.Service;

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
        if (menuRepository.existsByName(menuInputDto.getName())) {
            throw new MenuAlreadyExistsException("Menu with name '" + menuInputDto.getName() + "' already exists");
        }

        Menu menu = MenuMapper.fromInputDtoToModel(menuInputDto);

        menuRepository.save(menu);

         return MenuMapper.fromModelToOutputDto(menu);

    }
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public void deleteMenu(Long id){
        Menu menu= menuRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("no menu found with id" + id ));
        menuRepository.delete(menu);
    }

    public void assignDrinkToMenu(Long menuId, Long drinkId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RecordNotFoundException("No Menu found with id " + menuId));
        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new RecordNotFoundException("No Drink found with id " + drinkId));

        drink.setMenu(menu);

        menu.getDrinks().add(drink);

        drinkRepository.save(drink);
        menuRepository.save(menu);
    }

    public List<Drink> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No Menu found with id " + id));
        return menu.getDrinks();
    }

}
