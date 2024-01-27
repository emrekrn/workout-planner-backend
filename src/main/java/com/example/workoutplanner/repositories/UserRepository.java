package com.example.workoutplanner.repositories;

import com.example.workoutplanner.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findFirstByEmail(String email);
    boolean existsByEmail(String email);

}
