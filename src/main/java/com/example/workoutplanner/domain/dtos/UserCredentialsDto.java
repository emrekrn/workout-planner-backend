package com.example.workoutplanner.domain.dtos;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String email;
    private String password;
}
