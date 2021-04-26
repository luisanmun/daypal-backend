package com.bezkoder.spring.security.postgresql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.security.services.ExerciseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ExerciseController {
	
	
	@Autowired
	private ExerciseService exerciseService;
	
	@GetMapping(value = "/exercises")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Exercise> getExercises() {
		List<Exercise> exercises = (List<Exercise>) this.exerciseService.findAll();
		return exercises;
	}
}
