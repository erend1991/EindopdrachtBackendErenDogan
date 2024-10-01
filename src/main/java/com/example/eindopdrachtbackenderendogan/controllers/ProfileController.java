package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<ProfileOutputDto> createProfile(@Valid @RequestBody ProfileInputDto profileInputDto, @AuthenticationPrincipal UserDetails userDetails) {
        ProfileOutputDto profileOutputDto = profileService.createProfile(profileInputDto, userDetails);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(profileOutputDto.getUsername()).toUri();
        return ResponseEntity.created(uri).body(profileOutputDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfileOutputDto> getProfile(@PathVariable Long id) {
        ProfileOutputDto profileOutputDto = profileService.getProfileByUsername(id);
        return ResponseEntity.ok(profileOutputDto);
    }

    @GetMapping
    public ResponseEntity<List<ProfileOutputDto>> getAllProfiles() {
        List<ProfileOutputDto> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileOutputDto> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileInputDto profileInputDto) {
        ProfileOutputDto profileOutputDto = profileService.updateProfile(id, profileInputDto);
        return ResponseEntity.ok(profileOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok("Profile deleted successfully");
    }


}
