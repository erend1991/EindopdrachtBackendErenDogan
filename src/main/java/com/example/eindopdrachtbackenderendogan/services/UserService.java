package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.UserInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.UserOutputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.UserMapper;
import com.example.eindopdrachtbackenderendogan.models.*;
import com.example.eindopdrachtbackenderendogan.repositories.ProfileRepository;
import com.example.eindopdrachtbackenderendogan.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepos;
    private final ProfileRepository profileRepos;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepos, ProfileRepository profileRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.profileRepos = profileRepos;
        this.encoder = encoder;
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        User newUser = UserMapper.fromInputDtoToModel(userInputDto);
        newUser.setPassword(encoder.encode(userInputDto.getPassword()));
        userRepos.save(newUser);


        return UserMapper.fromModelToOutputDto(newUser);
    }


    public void assignUserToProfile(String username, Long id) {
        User user = userRepos.findById(username).orElseThrow(() -> new RuntimeException("No username found"));
        Profile profile = profileRepos.findById(id).orElseThrow(() -> new RuntimeException("no profile found with id" + id));
        profile.setUser(user);
        profileRepos.save(profile);
    }


    public List<User> getAllUsers() {return  userRepos.findAll();}


}












