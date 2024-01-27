package com.example.workoutplanner.services.impls;

import com.example.workoutplanner.domain.dtos.UserCredentialsDto;
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
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getFirstName())
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
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .build());
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) {

        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build());

        return UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userDto.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .userId(id)
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstName(userDto.getLastName())
                .lastName(userDto.getLastName())
                .build());

        return UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userDto.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
    }

    @Override
    public Optional<UserDto> authenticateUser(UserCredentialsDto userCredentialsDto) {
        UserEntity userEntity =  userRepository.findFirstByEmail(userCredentialsDto.getEmail());
        if (userEntity == null) {
            return Optional.empty();
        }
        return Optional.of(UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build());
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
