package com.example.eindopdrachtbackenderendogan.services;

import com.example.eindopdrachtbackenderendogan.dtos.input.UserInputDto;
import com.example.eindopdrachtbackenderendogan.dtos.output.UserOutputDto;
import com.example.eindopdrachtbackenderendogan.dtos.mapper.UserMapper;
import com.example.eindopdrachtbackenderendogan.exceptions.BadRequestException;
import com.example.eindopdrachtbackenderendogan.exceptions.UsernameAlreadyExistsException;
import com.example.eindopdrachtbackenderendogan.exceptions.UsernameNotFoundException;
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
        if (userInputDto.getUsername() == null || userInputDto.getUsername().isEmpty()) {
            throw new BadRequestException("need username");
        }
        if (userInputDto.getPassword() == null || userInputDto.getPassword().isEmpty()) {
            throw new BadRequestException("need password");

        }
        if (userInputDto.getRoles() == null || userInputDto.getRoles().length == 0) {
            throw new BadRequestException("need role");

        }
        if (userRepos.existsByUsername(userInputDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username " + userInputDto.getUsername() + " already exist.");
        }


        User newUser = UserMapper.fromInputDtoToModel(userInputDto);
        newUser.setPassword(encoder.encode(userInputDto.getPassword()));
        userRepos.save(newUser);


        return UserMapper.fromModelToOutputDto(newUser);
    }


    public void assignUserToProfile(String username, Long id) {
        User user = userRepos.findById(username).orElseThrow(() -> new UsernameNotFoundException("No username found"));
        Profile profile = profileRepos.findById(id).orElseThrow(() -> new RuntimeException("no profile found with id" + id));
        profile.setUser(user);
        profileRepos.save(profile);
    }


    public List<User> getAllUsers() {
        return userRepos.findAll();
    }


    public void deleteUser(String username) {
        User user = userRepos.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        userRepos.delete(user);
    }
}












