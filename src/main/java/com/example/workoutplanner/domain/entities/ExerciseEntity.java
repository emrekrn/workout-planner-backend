package com.example.workoutplanner.domain.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_generator")
    @Column(name = "id")
    private Integer exerciseId;

    private String name;
    private String description;

    @ElementCollection
    private List<SetEntity> sets;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workoutId")
    private WorkoutEntity workoutEntity;


}
