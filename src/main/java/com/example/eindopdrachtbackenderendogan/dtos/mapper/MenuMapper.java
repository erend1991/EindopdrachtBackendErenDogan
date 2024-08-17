package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Drink;
import com.example.eindopdrachtbackenderendogan.models.Menu;

import java.util.List;
import java.util.stream.Collectors;





public  class  MenuMapper {


        public static Menu fromInputDtoToModel(MenuInputDto menuInputDto, List<Drink> drinks) {
            Menu menu = new Menu();
            menu.setName(menuInputDto.getName());
            menu.setDrinks(drinks);

            return menu;
        }

        public static MenuOutputDto fromModelToOutputDto(Menu menu) {
            MenuOutputDto menuOutputDto = new MenuOutputDto();

            menuOutputDto.setId(menu.getId());
            menuOutputDto.setName(menu.getName());


            List<DrinkOutputDto> drinkDtos = menu.getDrinks().stream().map(DrinkMapper::fromModelToOutputDto).collect(Collectors.toList());

            menuOutputDto.setDrinks(drinkDtos);

            return menuOutputDto;

        }
    }

