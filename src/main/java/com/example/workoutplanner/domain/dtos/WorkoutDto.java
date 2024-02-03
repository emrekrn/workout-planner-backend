package com.example.workoutplanner.domain.dtos;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutDto {
    private Integer workoutId;
    @Column(name = "name")
    private String workoutName;

    private int userId;

}
