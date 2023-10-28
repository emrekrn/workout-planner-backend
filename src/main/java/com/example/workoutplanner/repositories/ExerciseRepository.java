package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.ExerciseEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<ExerciseEntity, Integer> {
}
