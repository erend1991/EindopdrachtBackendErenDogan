package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileOutputDto> createProfile(@Valid @RequestBody ProfileInputDto profileInputDto) {
        ProfileOutputDto profileOutputDto = profileService.createProfile(profileInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(profileOutputDto.getUsername()).toUri();
        return ResponseEntity.created(uri).body(profileOutputDto);
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileOutputDto> getProfile(@PathVariable String username) {
        ProfileOutputDto profileOutputDto = profileService.getProfileByUsername(username);
        return ResponseEntity.ok(profileOutputDto);
    }

    @GetMapping
    public ResponseEntity<List<ProfileOutputDto>> getAllProfiles() {
        List<ProfileOutputDto> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{username}")
    public ResponseEntity<ProfileOutputDto> updateProfile(@PathVariable String username, @Valid @RequestBody ProfileInputDto profileInputDto) {
        ProfileOutputDto profileOutputDto = profileService.updateProfile(username, profileInputDto);
        return ResponseEntity.ok(profileOutputDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteProfile(@PathVariable String username) {
        profileService.deleteProfile(username);
        return ResponseEntity.ok("Profile deleted successfully");
    }
}
