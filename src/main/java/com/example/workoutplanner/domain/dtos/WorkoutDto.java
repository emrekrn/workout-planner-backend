package com.example.workoutplanner.domain.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkoutDto {
    private Integer workoutId;
    private String name;
    private LocalDateTime startingTime;
    private LocalDateTime finishingTime;
}
