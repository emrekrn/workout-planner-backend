package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.WorkoutDto;
import com.example.workoutplanner.services.UserService;
import com.example.workoutplanner.services.WorkoutService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workouts")
@CrossOrigin
@Slf4j
public class WorkoutController {

    private WorkoutService workoutService;
    private UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    @GetMapping("/get-workouts/{userId}")
    public ResponseEntity<List<WorkoutDto>> findAllWorkoutsByUserId(@PathVariable Integer userId) {
        log.warn("UserID: {}", userId);
        if (!userService.doesUserExist(userId)) {
            log.warn("user does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<WorkoutDto> workoutDtos = workoutService.findWorkoutsByUserId(userId).orElse(Collections.EMPTY_LIST);

        return new ResponseEntity<>(workoutDtos, HttpStatus.OK);
    }


    @PostMapping("/create-workout")
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto){
        Optional<WorkoutDto> workoutDtoOptional = workoutService.createWorkout(workoutDto);
        return workoutDtoOptional
                .map(workoutDto1 -> new ResponseEntity<>(workoutDto1, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable("id") Integer id, @RequestBody WorkoutDto workoutDto) {
        if (!workoutService.doesWorkoutExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workoutService.updateWorkout(id, workoutDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteWorkout(@PathVariable("{id}") Integer id) {
        if (!workoutService.doesWorkoutExist(id)) {
            return HttpStatus.NOT_FOUND;
        }
        workoutService.deleteWorkout(id);
        return HttpStatus.OK;
    }
}
