package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.dtos.input.ProfileInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.ProfileOutputDto;
import com.example.eindopdrachtbackenderendogan.security.JwtService;
import com.example.eindopdrachtbackenderendogan.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProfileController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class ProfileControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    ProfileService profileService;

    @Test
    @WithMockUser(username = "testuser", roles="USER")
    void shouldCreateProfile() throws Exception {
        ProfileInputDto profileInputDto = new ProfileInputDto();
        profileInputDto.setUsername("erennnn");
        profileInputDto.setFirstname("Eren");
        profileInputDto.setLastname("Dogan");
        profileInputDto.setAddress("straat 1");
        profileInputDto.setPhoneNumber(612345567);
        profileInputDto.setEmail("eren@gmail.com");

        ProfileOutputDto profileOutputDto = new ProfileOutputDto();
        profileOutputDto.setUsername("erennnn");
        profileOutputDto.setFirstname("Eren");
        profileOutputDto.setLastname("Dogan");
        profileOutputDto.setAddress("straat 1");
        profileOutputDto.setPhoneNumber(612345567);
        profileOutputDto.setEmail("eren@gmail.com");

        Mockito.when(profileService.createProfile(Mockito.any(ProfileInputDto.class)))
                .thenReturn(profileOutputDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/profiles"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.username", is("erennnn")))
                .andExpect(jsonPath("$.firstname", is("Eren")))
                .andExpect(jsonPath("$.lastname", is("Dogan")))
                .andExpect(jsonPath("$.address", is("straat 1")))
                .andExpect(jsonPath("$.phoneNumber", is(612345567)))
                .andExpect(jsonPath("$.email", is("eren@gmail.com")));
    }
}
