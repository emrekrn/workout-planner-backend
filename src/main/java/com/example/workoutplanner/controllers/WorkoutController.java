package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.WorkoutDto;
import com.example.workoutplanner.services.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDto>> findAllWorkouts() {
        return new ResponseEntity<>(workoutService.findAllWorkouts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto> getWorkoutById(Integer id) {
        return workoutService.findWorkoutById(id)
                .map(workoutDto -> new ResponseEntity<>(workoutDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto){
        return new ResponseEntity<>(workoutService.createWorkout(workoutDto), HttpStatus.CREATED);
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
