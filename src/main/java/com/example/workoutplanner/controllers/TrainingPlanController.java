package com.example.workoutplanner.controllers;

import com.example.workoutplanner.domain.dtos.TrainingPlanDto;
import com.example.workoutplanner.services.TrainingPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/trainingplans")
public class TrainingPlanController {

    TrainingPlanService trainingPlanService;

    public TrainingPlanController(TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @GetMapping()
    public ResponseEntity<List<TrainingPlanDto>>  getTrainingPlans() {
        return new ResponseEntity<>(trainingPlanService.getAllTrainingPlans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> getTrainingPlan(@PathVariable("id") Integer id) {
        Optional<TrainingPlanDto> foundTrainingPlanDto = trainingPlanService.getTrainingPlan(id);
        return foundTrainingPlanDto
                .map(foundTrainingPlanDto1 -> new ResponseEntity<>(foundTrainingPlanDto1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<TrainingPlanDto> createTrainingPlan(@RequestBody TrainingPlanDto trainingPlanDto) {
        return new ResponseEntity<>(trainingPlanService.createTrainingPlan(trainingPlanDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> updateTrainingPlan(@PathVariable("id") Integer id, @RequestBody TrainingPlanDto trainingPlanDto) {
        if (!trainingPlanService.doesTrainingPlanExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(trainingPlanService.updateTrainingPlan(id, trainingPlanDto), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTrainingPlan(@PathVariable("id") Integer id) {
        trainingPlanService.deleteTrainingPlan(id);
        return HttpStatus.NOT_FOUND;
    }

}
