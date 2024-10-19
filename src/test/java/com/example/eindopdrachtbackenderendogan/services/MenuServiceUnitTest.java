package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.MenuInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.MenuMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.MenuOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Menu;
import com.example.eindopdrachtbackenderendogan.repositories.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MenuServiceUnitTest {


    Menu menu;

    @Mock
    MenuRepository menuRepository;


    @InjectMocks
    MenuService menuService;

    @Test
    void shouldCreateMenu() throws Exception {

        MenuInputDto menuInputDto = new MenuInputDto();
        menuInputDto.setName("Menu 1");


        Mockito.when(menuRepository.save(Mockito.any())).thenReturn(MenuMapper.fromInputDtoToModel(menuInputDto));

        MenuOutputDto menuOutputDto = menuService.createMenu(menuInputDto);


        assertEquals("Menu 1", menuOutputDto.getName());
    }
}


