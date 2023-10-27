package com.example.workoutplanner.services.impls;

import com.example.workoutplanner.domain.dtos.TrainingPlanDto;
import com.example.workoutplanner.domain.entities.TrainingPlanEntity;
import com.example.workoutplanner.repositories.TrainingPlanRepository;
import com.example.workoutplanner.services.TrainingPlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainingPlanServiceImpl implements TrainingPlanService {

    private final TrainingPlanRepository trainingPlanRepository;

    public TrainingPlanServiceImpl(TrainingPlanRepository trainingPlanRepository) {
        this.trainingPlanRepository = trainingPlanRepository;
    }

    @Override
    public List<TrainingPlanDto> getAllTrainingPlans() {
        List<TrainingPlanDto> trainingPlanDtos = new ArrayList<>();
        trainingPlanRepository
                .findAll()
                .forEach(trainingPlanEntity -> trainingPlanDtos.add(TrainingPlanDto.builder()
                        .trainingPlanId(trainingPlanEntity.getTrainingPlanId())
                        .name(trainingPlanEntity.getName())
                        .build()));
        return trainingPlanDtos;
    }

    @Override
    public Optional<TrainingPlanDto> getTrainingPlan(Integer id) {
        Optional<TrainingPlanEntity> foundTrainingPlanEntity = trainingPlanRepository.findById(id);

        if (foundTrainingPlanEntity.isEmpty()) {
            return Optional.empty();
        }
        return foundTrainingPlanEntity
                .map(trainingPlanEntity -> TrainingPlanDto.builder()
                        .trainingPlanId(trainingPlanEntity.getTrainingPlanId())
                        .name(trainingPlanEntity.getName())
                        .build());
    }

    @Override
    public TrainingPlanDto createTrainingPlan(TrainingPlanDto trainingPlanDto) {
        TrainingPlanEntity trainingPlanEntity = trainingPlanRepository.save(TrainingPlanEntity.builder()
                .name(trainingPlanDto.getName())
                .build());
        return TrainingPlanDto.builder()
                .trainingPlanId(trainingPlanEntity.getTrainingPlanId())
                .name(trainingPlanEntity.getName())
                .build();
    }

    @Override
    public TrainingPlanDto updateTrainingPlan(Integer id, TrainingPlanDto trainingPlanDto) {
        TrainingPlanEntity trainingPlanEntity = trainingPlanRepository.findById(id).get();
        trainingPlanEntity.setName(trainingPlanDto.getName());
        trainingPlanRepository.save(trainingPlanEntity);

        return TrainingPlanDto.builder()
                .trainingPlanId(trainingPlanEntity.getTrainingPlanId())
                .name(trainingPlanEntity.getName())
                .build();
    }

    @Override
    public void deleteTrainingPlan(Integer id) {
        trainingPlanRepository.deleteById(id);
    }

    @Override
    public boolean doesTrainingPlanExist(Integer id) {
        return trainingPlanRepository.existsById(id);
    }


}
