package com.example.workoutplanner.domain.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlanDto {
    private Integer trainingPlanId;
    private String name;
}
