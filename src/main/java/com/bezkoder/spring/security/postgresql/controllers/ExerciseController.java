package com.bezkoder.spring.security.postgresql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.repository.ExerciseRepository;
import com.bezkoder.spring.security.postgresql.security.services.ExerciseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ExerciseController {
	
	
	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@GetMapping(value = "/exercises")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Exercise> getExercises() {
		List<Exercise> exercises = (List<Exercise>) this.exerciseService.findAll();
		return exercises;
	}
	
	@DeleteMapping("/exercises/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteExercise(@PathVariable("id") long id) {
		try {
			exerciseRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}