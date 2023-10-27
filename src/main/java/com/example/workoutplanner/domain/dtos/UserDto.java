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
    private String firstname;
    private String lastname;

}
