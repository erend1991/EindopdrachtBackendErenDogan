package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.mapper.RoleMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.RoleOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Role;
import com.example.eindopdrachtbackenderendogan.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<RoleOutputDto> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        List<RoleOutputDto> allRolesOutput = new ArrayList<>();

        for (Role r : allRoles) {
            allRolesOutput.add(RoleMapper.fromModelToOutputDto(r));


        }
        return allRolesOutput;

    }
}
