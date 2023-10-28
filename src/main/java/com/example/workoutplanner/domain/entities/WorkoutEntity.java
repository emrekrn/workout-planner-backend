package com.example.workoutplanner.domain.entities;

import com.example.workoutplanner.domain.WorkoutStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "workout")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_sequence")
    @Column(name = "id")
    private Integer workoutId;
    private String name;
    private LocalDateTime startingTime;
    private LocalDateTime finishingTime;
    private WorkoutStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserEntity user;

}
