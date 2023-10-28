package com.example.workoutplanner.services.impls;

import com.example.workoutplanner.domain.dtos.WorkoutDto;
import com.example.workoutplanner.domain.entities.WorkoutEntity;
import com.example.workoutplanner.repositories.WorkoutRepository;
import com.example.workoutplanner.services.WorkoutService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutServiceImpl implements WorkoutService {

    private WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public List<WorkoutDto> findAllWorkouts() {
        List<WorkoutDto> workoutDtos = new ArrayList<>();
        workoutRepository.findAll().forEach(workoutEntity -> workoutDtos.add(
                WorkoutDto.builder()
                        .workoutId(workoutEntity.getWorkoutId())
                        .name(workoutEntity.getName())
                        .workoutStatus(workoutEntity.getStatus())
                        .startingTime(workoutEntity.getStartingTime())
                        .finishingTime(workoutEntity.getFinishingTime())
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
                        .name(workoutEntity.getName())
                        .workoutStatus(workoutEntity.getStatus())
                        .startingTime(workoutEntity.getStartingTime())
                        .finishingTime(workoutEntity.getFinishingTime())
                        .build());
    }

    @Override
    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        WorkoutEntity workoutEntity = WorkoutEntity.builder()
                .name(workoutDto.getName())
                .status(workoutDto.getWorkoutStatus())
                .startingTime(workoutDto.getStartingTime())
                .finishingTime(workoutDto.getFinishingTime())
                .build();

        workoutRepository.save(workoutEntity);

        return WorkoutDto.builder()
                .workoutId(workoutEntity.getWorkoutId())
                .name(workoutEntity.getName())
                .workoutStatus(workoutEntity.getStatus())
                .startingTime(workoutEntity.getStartingTime())
                .finishingTime(workoutEntity.getFinishingTime())
                .build();
    }

    @Override
    public WorkoutDto updateWorkout(Integer id, WorkoutDto workoutDto) {
        WorkoutEntity workoutEntity = workoutRepository.save(WorkoutEntity.builder()
                .workoutId(id)
                .name(workoutDto.getName())
                .status(workoutDto.getWorkoutStatus())
                .startingTime(workoutDto.getStartingTime())
                .finishingTime(workoutDto.getFinishingTime())
                .build());

        return WorkoutDto.builder()
                .workoutId(workoutEntity.getWorkoutId())
                .name(workoutEntity.getName())
                .workoutStatus(workoutEntity.getStatus())
                .startingTime(workoutEntity.getStartingTime())
                .finishingTime(workoutEntity.getFinishingTime())
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
