package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.UserCredentialsDto;
import com.example.workoutplanner.domain.dtos.UserDto;
import com.example.workoutplanner.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserCredentialsDto userCredentialsDto) {
        Optional<UserDto> userDto = userService.authenticateUser(userCredentialsDto);
        return userDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
    }
}
