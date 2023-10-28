package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.ExerciseDto;
import com.example.workoutplanner.services.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercises(){
        return new ResponseEntity<>(exerciseService.findAllExercises(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable("id") Integer id) {
        return exerciseService.findExerciseById(id)
                .map(exerciseDto -> new ResponseEntity<>(exerciseDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        return new ResponseEntity<>(exerciseService.createExercise(exerciseDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable("id") Integer id, @RequestBody ExerciseDto exerciseDto) {
        if (exerciseService.doesExerciseExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(exerciseService.updateExercise(id, exerciseDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteExercise(@PathVariable("id") Integer id) {
        if (!exerciseService.doesExerciseExist(id)) {
            return  HttpStatus.NOT_FOUND;
        }
        exerciseService.deleteExercise(id);
        return HttpStatus.OK;
    }

}
