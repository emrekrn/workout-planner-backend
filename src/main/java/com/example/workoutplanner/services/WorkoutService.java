package com.example.workoutplanner.services;

import com.example.workoutplanner.domain.dtos.WorkoutDto;
import com.example.workoutplanner.domain.entities.UserEntity;
import com.example.workoutplanner.domain.entities.WorkoutEntity;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    List<WorkoutDto> findAllWorkouts();
    Optional<WorkoutDto> findWorkoutById(Integer id);
    Optional<List<WorkoutDto>> findWorkoutsByUserId(Integer userId);
    Optional<WorkoutDto> createWorkout(WorkoutDto workoutDto);
    WorkoutDto updateWorkout(Integer id, WorkoutDto workoutDto);
    void deleteWorkout(Integer id);
    boolean doesWorkoutExist(Integer id);
}
