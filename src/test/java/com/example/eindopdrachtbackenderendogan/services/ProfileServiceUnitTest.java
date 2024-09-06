package com.example.eindopdrachtbackenderendogan.services;
import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.DrinkMapper;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.ProfileMapper;
import com.example.eindopdrachtbackenderendogan.dtos.output.DrinkOutputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.models.Profile;
import com.example.eindopdrachtbackenderendogan.models.User;
import com.example.eindopdrachtbackenderendogan.repositories.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
class ProfileServiceUnitTest {


    @Mock
    ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void shouldGetProfileById() {
        Long profileId = 1L;

        User user = new User();
        user.setUsername("eren");

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setId(profileId);
        profile.setFirstname("Eren");
        profile.setLastname("Dogan");
        profile.setAddress("straat 1");
        profile.setPhoneNumber(612345567);
        profile.setEmail("eren@gmail.com");

        ProfileOutputDto profileOutputDto = new ProfileOutputDto();

        profileOutputDto.setId(profileId);
        profileOutputDto.setUsername("eren");
        profileOutputDto.setFirstname("Eren");
        profileOutputDto.setLastname("Dogan");
        profileOutputDto.setAddress("straat 1");
        profileOutputDto.setPhoneNumber(612345567);
        profileOutputDto.setEmail("eren@gmail.com");

       Mockito.when(profileRepository.findById(anyLong())).thenReturn(Optional.of(profile));

        ProfileOutputDto result = profileService.getProfileByUsername(profileId);

        assertEquals(profileId, result.getId());
        assertEquals("eren", result.getUsername());
        assertEquals("Eren", result.getFirstname());
        assertEquals("Dogan", result.getLastname());
        assertEquals("straat 1", result.getAddress());
        assertEquals(612345567, result.getPhoneNumber());
        assertEquals("eren@gmail.com", result.getEmail());
    }

    }

