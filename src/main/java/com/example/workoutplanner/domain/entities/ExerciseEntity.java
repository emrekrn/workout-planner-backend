package com.example.workoutplanner.domain.entities;


import jakarta.persistence.*;

@Entity
@Table
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_generator")
    @Column(name = "id")
    private Integer exerciseId;

    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workoutId")
    private WorkoutEntity workoutEntity;


}
