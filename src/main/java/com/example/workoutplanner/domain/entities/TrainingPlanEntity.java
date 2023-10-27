package com.example.workoutplanner.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "training_plan")
@Entity
public class TrainingPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_id_generator")
    @Column(name = "id")
    private Integer trainingPlanId;
    private String name;
    @CreationTimestamp
    private Date creationDate;
    @UpdateTimestamp
    private Date updateDate;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "workout_trainingplan",
            joinColumns = @JoinColumn(name = "workoutId"),
            inverseJoinColumns = @JoinColumn(name = "trainingPlanId")
    )
    private List<WorkoutEntity> workoutEntities;

}
