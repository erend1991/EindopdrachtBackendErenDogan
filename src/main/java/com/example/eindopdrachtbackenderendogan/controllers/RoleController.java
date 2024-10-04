package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.output.RoleOutputDto;
import com.example.eindopdrachtbackenderendogan.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


        @RestController
        @RequestMapping("/roles")
        public class RoleController {

            private final RoleService roleService;

            public RoleController(RoleService roleService){
                this.roleService = roleService;
            }

            @GetMapping
            public ResponseEntity<List<RoleOutputDto>> getAllRoles() {
                List<RoleOutputDto> roles = roleService.getAllRoles();
                return ResponseEntity.ok().body(roleService.getAllRoles());
            }
    }


