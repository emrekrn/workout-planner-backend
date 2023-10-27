package com.example.workoutplanner.services.impls;

import com.example.workoutplanner.domain.dtos.UserDto;
import com.example.workoutplanner.domain.entities.UserEntity;
import com.example.workoutplanner.repositories.UserRepository;
import com.example.workoutplanner.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        userRepository
                .findAll()
                .forEach(userEntity -> users.add(UserDto.builder()
                        .userId(userEntity.getUserId())
                        .username(userEntity.getUsername())
                        .email(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .firstname(userEntity.getFirstname())
                        .lastname(userEntity.getLastname())
                        .build()));

        return users;
    }

    @Override
    public Optional<UserDto> getUser(Integer id) {
        Optional<UserEntity> foundUserEntity = userRepository.findById(id);

        if (foundUserEntity.isEmpty()) {
            return Optional.empty();
        } else {
            return foundUserEntity.map(userEntity -> UserDto.builder()
                    .userId(userEntity.getUserId())
                    .username(userEntity.getUsername())
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .firstname(userEntity.getFirstname())
                    .lastname(userEntity.getLastname())
                    .build());
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) {

        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .build());

        return UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userDto.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userEntity.getLastname())
                .build();
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .userId(id)
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .build());

        return UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userDto.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userEntity.getLastname())
                .build();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean doesUserExist(Integer id) {
        return !userRepository.existsById(id);
    }

}
