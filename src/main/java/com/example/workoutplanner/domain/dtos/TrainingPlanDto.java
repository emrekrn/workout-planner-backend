package com.example.workoutplanner.domain.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingPlanDto {
    private Integer id;
    private String name;
}
