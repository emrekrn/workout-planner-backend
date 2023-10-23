package com.example.workoutplanner.services;

import com.example.workoutplanner.domain.dtos.UserDto;
import com.example.workoutplanner.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserDto> getUsers();
    Optional<UserDto> getUser(Integer id);
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);
    boolean doesUserExist(Integer id);

}
