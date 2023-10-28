package com.example.workoutplanner.domain.dtos;

import com.example.workoutplanner.domain.WorkoutStatus;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutDto {
    private Integer workoutId;
    private String name;
    private WorkoutStatus workoutStatus;
    private LocalDateTime startingTime;
    private LocalDateTime finishingTime;

}
