package com.example.eindopdrachtbackenderendogan.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfileInputDto {

    @NotBlank(message = "fill in your username")
    private String username;

    @NotBlank(message = "fill in your firstname")
    private String firstname;

    @NotBlank(message = "fill in your lastname")
    private String lastname;

    @NotBlank(message = "fill in your adress")
    private String address;

    @NotBlank
    @Size(min = 10, max = 15)
    private String phoneNumber;

    @NotBlank()
    @Email(message = "email not valid")
    private String email;



    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
