package com.example.workoutplanner.domain.dtos;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDto {

    private Integer exerciseId;
    private String name;
    private String description;
}
