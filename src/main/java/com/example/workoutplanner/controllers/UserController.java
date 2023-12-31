package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.UserDto;
import com.example.workoutplanner.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        Optional<UserDto> userDto = userService.getUser(id);

        return userDto
                .map(userDto1 -> new ResponseEntity<>(userDto1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        if (userService.doesUserExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteUser(@PathVariable("id") Integer id) {
        if (userService.doesUserExist(id)) {
            return HttpStatus.NOT_FOUND;
        }
        else {
            userService.deleteUser(id);
            return HttpStatus.OK;
        }
    }

}
