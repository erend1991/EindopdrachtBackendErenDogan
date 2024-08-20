package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileDto;
import com.example.eindopdrachtbackenderendogan.models.Profile;
import com.example.eindopdrachtbackenderendogan.repositories.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



    @RestController
    @RequestMapping("/profiles")
    public class ProfileController {

        // No ProfileService used in demo code!

        private final ProfileRepository repos;

        public ProfileController(ProfileRepository repos) {
            this.repos = repos;
        }

        @PostMapping
        public ResponseEntity<Profile> createProfile(@RequestBody ProfileDto profileDto) {
            Profile profile = new Profile();
            profile.setUsername(profileDto.username);
            profile.setFirstname(profileDto.firstname);
            profile.setLastname(profileDto.lastname);
            profile.setAddress(profileDto.address);

            this.repos.save(profile);

            return ResponseEntity.created(null).body(profile);
        }

        @GetMapping("/{username}")
        public ResponseEntity<ProfileDto> getProfile(@PathVariable String username,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
            if (username.equals(userDetails.getUsername())) {
                Profile profile = this.repos.findById(username).get();  // happy flow
                ProfileDto profileDto = new ProfileDto();
                profileDto.username = profile.getUsername();
                profileDto.firstname = profile.getFirstname();
                profileDto.lastname = profile.getLastname();
                profileDto.address = profile.getAddress();

                return ResponseEntity.ok(profileDto);
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
    }


