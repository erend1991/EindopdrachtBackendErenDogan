package com.example.eindopdrachtbackenderendogan.exceptions;

public class UsernameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("Can not find user" + username);
    }
}

