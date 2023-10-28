package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<WorkoutEntity, Integer> {
}
