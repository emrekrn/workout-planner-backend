package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.UserCredentialsDto;
import com.example.workoutplanner.domain.dtos.UserDto;
import com.example.workoutplanner.exception.UserExistsException;
import com.example.workoutplanner.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/")
@Slf4j
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserCredentialsDto userCredentialsDto) {
        Optional<UserDto> userDto = userService.authenticateUser(userCredentialsDto);
        return userDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

        try {
            if (userService.checkIfUserEmailExists(userDto.getEmail())){
                throw new UserExistsException("User email already exists");
            }
        }
        catch (UserExistsException e) {
            log.warn("User email already exists. Email: {}. Error: {}", userDto.getEmail(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


        UserDto userDto1 =  userService.saveUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
}
