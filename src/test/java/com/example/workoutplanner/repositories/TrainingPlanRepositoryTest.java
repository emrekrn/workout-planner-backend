package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.TrainingPlanEntity;
import com.example.workoutplanner.domain.entities.UserEntity;
import com.example.workoutplanner.domain.entities.WorkoutEntity;
import com.example.workoutplanner.domain.entities.WorkoutStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainingPlanRepositoryTest {

    @Autowired
    TrainingPlanRepository  trainingPlanRepository;

    @Test
    public void createTrainingPlanWithUser() {
        UserEntity userEntity = UserEntity.builder()
                .username("emrecank")
                .firstname("emrecan")
                .lastname("koran")
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
                .startingTime(LocalDateTime.of(2023,10,27,12,13))
                .finishingTime(LocalDateTime.of(2023,10,27,12,50))
                .status(WorkoutStatus.NotStarted)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .username("emrecanTest")
                .firstname("emrecan")
                .lastname("koran")
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