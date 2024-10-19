package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Menu;


public class MenuMapper {


    public static Menu fromInputDtoToModel(MenuInputDto menuInputDto) {
        Menu menu = new Menu();
        menu.setName(menuInputDto.getName());

        return menu;
    }

    public static MenuOutputDto fromModelToOutputDto(Menu menu) {
        MenuOutputDto menuOutputDto = new MenuOutputDto();

        menuOutputDto.setId(menu.getId());
        menuOutputDto.setName(menu.getName());


        return menuOutputDto;

    }
}

