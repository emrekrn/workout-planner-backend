package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.UserEntity;
import com.example.workoutplanner.domain.entities.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<WorkoutEntity, Integer> {

    List<WorkoutEntity> getAllByUser(UserEntity userEntity);
}
