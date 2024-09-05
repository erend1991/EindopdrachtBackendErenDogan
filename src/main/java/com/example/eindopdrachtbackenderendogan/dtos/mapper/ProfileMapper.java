package com.example.eindopdrachtbackenderendogan.dtos.mapper;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Profile;

public class ProfileMapper {

    public static Profile fromInputDtoToModel(ProfileInputDto profileInputDto) {
        Profile profile = new Profile();

        profile.setEmail(profileInputDto.getUsername());
        profile.setFirstname(profileInputDto.getFirstname());
        profile.setLastname(profileInputDto.getLastname());
        profile.setAddress(profileInputDto.getAddress());
        profile.setPhoneNumber(profileInputDto.getPhoneNumber());
        profile.setEmail(profileInputDto.getEmail());

        return profile;
    }

    public static ProfileOutputDto fromModelToOutputDto(Profile profile) {

        ProfileOutputDto outputDto = new ProfileOutputDto();
        outputDto.setUsername(profile.getUser().getUsername());
        outputDto.setId(profile.getId());
        outputDto.setFirstname(profile.getFirstname());
        outputDto.setLastname(profile.getLastname());
        outputDto.setAddress(profile.getAddress());
        outputDto.setPhoneNumber(profile.getPhoneNumber());
        outputDto.setEmail(profile.getEmail());

        return outputDto;
    }
}
