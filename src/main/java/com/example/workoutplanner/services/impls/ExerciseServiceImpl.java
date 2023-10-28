package com.example.workoutplanner.services.impls;

import com.example.workoutplanner.domain.dtos.ExerciseDto;
import com.example.workoutplanner.domain.entities.ExerciseEntity;
import com.example.workoutplanner.repositories.ExerciseRepository;
import com.example.workoutplanner.services.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseDto> findAllExercises() {
        List<ExerciseDto> exerciseDtos = new ArrayList<>();

        exerciseRepository.findAll().forEach(exerciseEntity -> exerciseDtos.add(
                ExerciseDto.builder()
                        .exerciseId(exerciseEntity.getExerciseId())
                        .name(exerciseEntity.getName())
                        .description(exerciseEntity.getDescription())
                        .setEntities(exerciseEntity.getSets())
                        .build()
        ));
        return exerciseDtos;
    }

    @Override
    public Optional<ExerciseDto> findExerciseById(Integer id) {
        Optional<ExerciseEntity> exerciseEntity = exerciseRepository.findById(id);

        if (exerciseEntity.isEmpty()) {
            return Optional.empty();
        }

        return exerciseEntity.map(exerciseEntity1 -> ExerciseDto.builder()
                .exerciseId(exerciseEntity1.getExerciseId())
                .name(exerciseEntity1.getName())
                .description(exerciseEntity1.getDescription())
                .setEntities(exerciseEntity1.getSets())
                .build());
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        ExerciseEntity exerciseEntity = exerciseRepository.save(ExerciseEntity.builder()
                .name(exerciseDto.getName())
                .description(exerciseDto.getDescription())
                .sets(exerciseDto.getSetEntities())
                .build());

        return ExerciseDto.builder()
                .exerciseId(exerciseEntity.getExerciseId())
                .name(exerciseEntity.getName())
                .description(exerciseEntity.getDescription())
                .setEntities(exerciseEntity.getSets())
                .build();
    }

    @Override
    public ExerciseDto updateExercise(Integer id, ExerciseDto exerciseDto) {
        ExerciseEntity exerciseEntity = exerciseRepository.save(ExerciseEntity.builder()
                .exerciseId(id)
                .name(exerciseDto.getName())
                .description(exerciseDto.getDescription())
                .sets(exerciseDto.getSetEntities())
                .build());

        return ExerciseDto.builder()
                .exerciseId(exerciseEntity.getExerciseId())
                .name(exerciseEntity.getName())
                .description(exerciseEntity.getDescription())
                .setEntities(exerciseEntity.getSets())
                .build();
    }

    @Override
    public void deleteExercise(Integer id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public boolean doesExerciseExist(Integer id) {
        return exerciseRepository.existsById(id);
    }
}
