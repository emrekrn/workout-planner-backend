package com.example.workoutplanner.services;

import com.example.workoutplanner.domain.dtos.TrainingPlanDto;
import com.example.workoutplanner.repositories.TrainingPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TrainingPlanService {

    List<TrainingPlanDto> getAllTrainingPlans();
    Optional<TrainingPlanDto> getTrainingPlan(Integer id);
    TrainingPlanDto createTrainingPlan(TrainingPlanDto trainingPlanDto);
    TrainingPlanDto updateTrainingPlan(Integer id, TrainingPlanDto trainingPlanDto);
    void deleteTrainingPlan(Integer id);

    boolean doesTrainingPlanExist(Integer id);



}
