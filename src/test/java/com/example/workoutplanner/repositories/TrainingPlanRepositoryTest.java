package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.TrainingPlanEntity;
import com.example.workoutplanner.domain.entities.UserEntity;
import com.example.workoutplanner.domain.entities.WorkoutEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TrainingPlanRepositoryTest {

    @Autowired
    TrainingPlanRepository  trainingPlanRepository;

    @Test
    public void createTrainingPlanWithUser() {
        UserEntity userEntity = UserEntity.builder()
                .username("emrecank")
                .firstName("emrecan")
                .lastName("koran")
                .email("emrecan@koran.com")
                .password("123emr123")
                .build();

        TrainingPlanEntity trainingPlanEntity = TrainingPlanEntity.builder()
                .name("chest")
                .userEntity(userEntity)
                .build();

        trainingPlanRepository.save(trainingPlanEntity);
    }
    @Test
    public void createTrainingPlanWithWorkout() {
        WorkoutEntity workoutEntity = WorkoutEntity.builder()
                .name("Chest")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .username("emrecanTest")
                .firstName("emrecan")
                .lastName("koran")
                .email("emrecan@koran.com")
                .password("123emr123")
                .build();

        TrainingPlanEntity trainingPlanEntity = TrainingPlanEntity.builder()
                .name("chest")
                .userEntity(userEntity)
                .workoutEntities(List.of(workoutEntity))
                .build();

        trainingPlanRepository.save(trainingPlanEntity);
    }

}