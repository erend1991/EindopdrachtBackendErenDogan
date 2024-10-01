package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.UserInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.UserOutputDto;


import com.example.eindopdrachtbackenderendogan.models.User;
import com.example.eindopdrachtbackenderendogan.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserOutputDto> createUser(@RequestBody UserInputDto userInputDto) {
        UserOutputDto dto = userService.createUser(userInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(dto.getUsername()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/users/{username}/profile/{profileId}")
    public ResponseEntity<String> assignUserToProfile(@PathVariable String username, @PathVariable Long profileId) {
        userService.assignUserToProfile(username, profileId);
        return ResponseEntity.ok("user assigned to profile");
    }

  @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
      List<User> users = userService.getAllUsers();
      return ResponseEntity.ok(users);
  }
}
