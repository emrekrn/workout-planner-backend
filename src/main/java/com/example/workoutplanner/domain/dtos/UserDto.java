package com.example.workoutplanner.domain.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
