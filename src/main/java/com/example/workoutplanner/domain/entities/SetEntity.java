package com.example.workoutplanner.domain.entities;


import jakarta.persistence.Embeddable;

@Embeddable
public class SetEntity {
    public Integer setNumber;
    public Integer repeat;
}
