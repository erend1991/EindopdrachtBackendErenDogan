package com.example.eindopdrachtbackenderendogan.dtos.mapper;


        import com.example.eindopdrachtbackenderendogan.dtos.input.UserInputDto;
        import com.example.eindopdrachtbackenderendogan.dtos.output.UserOutputDto;
        import com.example.eindopdrachtbackenderendogan.models.User;
        import com.example.eindopdrachtbackenderendogan.models.Role;

        import java.util.HashSet;
        import java.util.Set;

public class UserMapper {

    public static User fromInputDtoToModel(UserInputDto userInputDto) {
        User user = new User();
        user.setUsername(userInputDto.getUsername());
        user.setPassword(userInputDto.getPassword());

        Set<Role> roles = new HashSet<>();
        for (String roleName : userInputDto.getRoles()) {
            Role role = new Role();
            role.setRolename("ROLE_" + roleName);
            roles.add(role);
        }
        user.setRoles(roles);

        return user;
    }

    public static UserOutputDto fromModelToOutputDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setUsername(user.getUsername());

        String[] roles = user.getRoles().stream()
                .map(Role::getRolename)
                .map(roleName -> roleName.replace("ROLE_", "")) // Remove the "ROLE_" prefix
                .toArray(String[]::new);
        userOutputDto.setRoles(roles);

        return userOutputDto;
    }
}

