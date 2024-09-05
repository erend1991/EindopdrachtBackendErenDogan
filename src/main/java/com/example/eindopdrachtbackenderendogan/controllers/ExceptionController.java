package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.exceptions.BadRequestException;
import com.example.eindopdrachtbackenderendogan.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



    @ControllerAdvice
    public class ExceptionController {

        @ExceptionHandler(value = RecordNotFoundException.class)
        public ResponseEntity<Object> exception(RecordNotFoundException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        }

        @ExceptionHandler(value = IndexOutOfBoundsException.class)
        public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {

            return new ResponseEntity<>("Dit id staat niet in de database", HttpStatus.NOT_FOUND);

        }



        @ExceptionHandler(value = BadRequestException.class)
        public ResponseEntity<String> exception(BadRequestException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = UsernameNotFoundException.class)
        public ResponseEntity<String> exception(UsernameNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }



    }

