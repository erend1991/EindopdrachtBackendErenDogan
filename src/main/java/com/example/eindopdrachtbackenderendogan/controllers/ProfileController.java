package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Profile;
import com.example.eindopdrachtbackenderendogan.services.PhotoService;
import com.example.eindopdrachtbackenderendogan.services.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    private final PhotoService photoService;



    public ProfileController(ProfileService profileService, PhotoService photoService) {
        this.profileService = profileService;
        this.photoService = photoService;
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

    @PostMapping("/{id}/photo")
    public ResponseEntity<Profile> addPhotoToStudent(@PathVariable Long id, @RequestBody MultipartFile file)

        throws IOException{
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/profiles")
                .path(Objects.requireNonNull(id.toString()))
                .path("/photo")
                .toUriString();
        String fileName = photoService.storeFile(file);
        Profile profile = profileService.assignPhotoToStudent(fileName, id);
        return ResponseEntity.created(URI.create(url)).body(profile);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getProfilePhoto(@PathVariable long id, HttpServletRequest request){
        Resource resource = profileService.getPhotoFromProfile(id);

        String image;

        try {
            image = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e){
            image = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename" + resource.getFilename())
                .body(resource);

    }



}
