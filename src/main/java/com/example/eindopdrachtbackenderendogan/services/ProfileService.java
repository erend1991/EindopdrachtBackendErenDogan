package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.ProfileMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackenderendogan.models.Profile;
import com.example.eindopdrachtbackenderendogan.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileOutputDto createProfile(ProfileInputDto profileInputDto) {
        Profile profile = ProfileMapper.fromInputDtoToModel(profileInputDto);
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.fromModelToOutputDto(savedProfile);
    }

    public List<ProfileOutputDto> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileOutputDto> profileDtos = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDtos.add(ProfileMapper.fromModelToOutputDto(profile));
        }
        return profileDtos;
    }

    public ProfileOutputDto getProfileByUsername(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No profile found with id " + id));
        return ProfileMapper.fromModelToOutputDto(profile);
    }

    public ProfileOutputDto updateProfile(Long id, ProfileInputDto profileInputDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No profile found with id " + id));

        profile.setFirstname(profileInputDto.getFirstname());
        profile.setLastname(profileInputDto.getLastname());
        profile.setAddress(profileInputDto.getAddress());
        profile.setPhoneNumber(profileInputDto.getPhoneNumber());
        profile.setEmail(profileInputDto.getEmail());

        Profile updatedProfile = profileRepository.save(profile);
        return ProfileMapper.fromModelToOutputDto(updatedProfile);
    }

    public void deleteProfile(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No profile found with id " + id);
        }
    }
}
