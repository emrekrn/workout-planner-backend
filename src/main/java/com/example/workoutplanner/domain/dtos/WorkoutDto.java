package com.example.workoutplanner.domain.dtos;

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
    private LocalDateTime startingTime;
    private LocalDateTime finishingTime;

}
