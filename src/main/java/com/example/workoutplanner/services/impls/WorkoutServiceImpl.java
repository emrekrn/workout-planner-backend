package com.example.workoutplanner.services.impls;

import com.example.workoutplanner.domain.dtos.WorkoutDto;
import com.example.workoutplanner.domain.entities.UserEntity;
import com.example.workoutplanner.domain.entities.WorkoutEntity;
import com.example.workoutplanner.exception.AuthenticationException;
import com.example.workoutplanner.exception.UserNotFoundException;
import com.example.workoutplanner.repositories.UserRepository;
import com.example.workoutplanner.repositories.WorkoutRepository;
import com.example.workoutplanner.services.WorkoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<WorkoutDto> findAllWorkouts() {
        List<WorkoutDto> workoutDtos = new ArrayList<>();
        workoutRepository.findAll().forEach(workoutEntity -> workoutDtos.add(
                WorkoutDto.builder()
                        .workoutId(workoutEntity.getWorkoutId())
                        .workoutName(workoutEntity.getName())
                        .build()
        ));
        return workoutDtos;
    }

    @Override
    public Optional<WorkoutDto> findWorkoutById(Integer id) {
        Optional<WorkoutEntity> foundWorkoutEntity = workoutRepository.findById(id);
        if (foundWorkoutEntity.isEmpty()) {
            return Optional.empty();
        }
        return foundWorkoutEntity.map(workoutEntity ->
                WorkoutDto.builder()
                        .workoutId(workoutEntity.getWorkoutId())
                        .workoutName(workoutEntity.getName())
                        .build());
    }

    @Override
    public Optional<List<WorkoutDto>> findWorkoutsByUserId(Integer userId) {
        try {
            UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("BenutzerId " + userId + " existiert nicht"));
            List<WorkoutEntity>  workoutEntities = workoutRepository.getAllByUser(userEntity);
            List<WorkoutDto> workoutDtos = workoutEntities
                    .stream()
                    .map(workoutEntity -> WorkoutDto.builder()
                            .userId(userId)
                            .workoutName(workoutEntity.getName())
                            .workoutId(workoutEntity.getWorkoutId())
                            .build())
                    .toList();
            return Optional.of(workoutDtos);
        }
        catch (UserNotFoundException e) {
            log.error("UserId: {}. Error: {}", userId, e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<WorkoutDto> createWorkout(WorkoutDto workoutDto) {

        try {
            UserEntity userEntity = userRepository
                    .findById(workoutDto.getUserId())
                    .orElseThrow(() -> new AuthenticationException("User does not exist"));
            WorkoutEntity workoutEntity = WorkoutEntity.builder()
                    .name(workoutDto.getWorkoutName())
                    .user(userEntity)
                    .build();

            workoutRepository.save(workoutEntity);

            return Optional.of(WorkoutDto.builder()
                    .workoutId(workoutEntity.getWorkoutId())
                    .workoutName(workoutEntity.getName())
                    .userId(userEntity.getUserId())
                    .build());
        }
        catch (AuthenticationException e) {
            return Optional.empty();
        }
    }

    @Override
    public WorkoutDto updateWorkout(Integer id, WorkoutDto workoutDto) {
        WorkoutEntity workoutEntity = workoutRepository.save(WorkoutEntity.builder()
                .workoutId(id)
                .name(workoutDto.getWorkoutName())
                .build());

        return WorkoutDto.builder()
                .workoutId(workoutEntity.getWorkoutId())
                .workoutName(workoutEntity.getName())
                .build();
    }

    @Override
    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);

    }

    @Override
    public boolean doesWorkoutExist(Integer id) {
        return workoutRepository.existsById(id);
    }
}
