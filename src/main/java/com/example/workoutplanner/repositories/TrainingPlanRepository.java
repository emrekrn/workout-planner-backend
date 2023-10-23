package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.TrainingPlanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingPlanRepository extends CrudRepository<TrainingPlanEntity, Integer> {
}
