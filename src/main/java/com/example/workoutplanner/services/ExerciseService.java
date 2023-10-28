package com.example.workoutplanner.services;

import com.example.workoutplanner.domain.dtos.ExerciseDto;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    List<ExerciseDto> findAllExercises();
    Optional<ExerciseDto> findExerciseById(Integer id);
    ExerciseDto createExercise(ExerciseDto exerciseDto);
    ExerciseDto updateExercise(Integer id, ExerciseDto exerciseDto);
    void deleteExercise(Integer id);
    boolean doesExerciseExist(Integer id);

}
