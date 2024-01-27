package com.example.workoutplanner.services;

import com.example.workoutplanner.domain.dtos.UserCredentialsDto;
import com.example.workoutplanner.domain.dtos.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserDto> getUsers();
    Optional<UserDto> getUser(Integer id);
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Integer id, UserDto userDto);

    Optional<UserDto> authenticateUser(UserCredentialsDto userCredentialsDto);
    void deleteUser(Integer id);
    boolean doesUserExist(Integer id);

}
