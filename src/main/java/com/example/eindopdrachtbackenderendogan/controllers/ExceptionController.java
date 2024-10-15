package com.example.eindopdrachtbackenderendogan.controllers;

import com.example.eindopdrachtbackenderendogan.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


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

        @ExceptionHandler(UsernameAlreadyExistsException.class)
        public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MenuAlreadyExistsException.class)
        public ResponseEntity<String> handleMenuAlreadyExistsException(MenuAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(DrinkCreateException.class)
        public ResponseEntity<String> handleDrinkCreationException(DrinkCreateException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

    @ExceptionHandler(DuplicateProfileException.class)
    public ResponseEntity<String> handleDuplicateProfileException(DuplicateProfileException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }






    }

