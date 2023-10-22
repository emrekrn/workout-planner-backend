package com.example.workoutplanner.domain.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;

}
