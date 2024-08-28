package com.example.eindopdrachtbackenderendogan.dtos.mapper;


import com.example.eindopdrachtbackenderendogan.dtos.input.RoleInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.RoleOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Role;

public class RoleMapper {

        public static Role fromInputDtoToModel(RoleInputDto roleInputDto) {
            Role role = new Role();
            role.setRolename(roleInputDto.getRoleName());
            return role;
        }

        public static RoleOutputDto fromModelToOutputDto(Role role) {
            RoleOutputDto roleOutputDto = new RoleOutputDto();
            roleOutputDto.setRoleName(role.getRolename());
            return roleOutputDto;
        }
    }


