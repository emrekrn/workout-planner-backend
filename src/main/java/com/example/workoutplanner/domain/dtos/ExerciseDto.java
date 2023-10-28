package com.example.workoutplanner.domain.dtos;
import com.example.workoutplanner.domain.entities.SetEntity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDto {

    private Integer exerciseId;
    private String name;
    private String description;
    private List<SetEntity> setEntities;

}
