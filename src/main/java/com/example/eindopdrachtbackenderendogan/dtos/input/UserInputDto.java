package com.example.eindopdrachtbackenderendogan.dtos.input;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserInputDto {

        @NotBlank(message = "need username")
        private String username;

        @NotBlank(message = "need password")
        private String password;

        @NotEmpty(message = "need role")
        private String[] roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }


