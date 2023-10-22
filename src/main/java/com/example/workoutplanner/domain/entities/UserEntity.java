package com.example.workoutplanner.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userId_generator")
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private Date created_on;
    private Date updated_on;

}
