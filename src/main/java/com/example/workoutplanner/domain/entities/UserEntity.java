package com.example.workoutplanner.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_generator")
    @Column(name = "id")
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @CreationTimestamp
    private Date created_on;
    @UpdateTimestamp
    private Date updated_on;

}
