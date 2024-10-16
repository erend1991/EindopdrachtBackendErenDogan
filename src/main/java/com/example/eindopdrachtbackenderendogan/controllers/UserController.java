package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.UserInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.UserOutputDto;


import com.example.eindopdrachtbackenderendogan.exceptions.UsernameNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.User;
import com.example.eindopdrachtbackenderendogan.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserOutputDto> createUser(@Valid @RequestBody UserInputDto userInputDto) {

        UserOutputDto dto = userService.createUser(userInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(dto.getUsername()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{username}/profiles/{profileId}")
    public ResponseEntity<String> assignUserToProfile(@Valid @PathVariable String username, @PathVariable Long profileId) {
        userService.assignUserToProfile(username, profileId);
        return ResponseEntity.ok("user assigned to profile");
    }

  @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
      List<User> users = userService.getAllUsers();
      return ResponseEntity.ok(users);
  }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("User " + username + " successfully deleted.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
