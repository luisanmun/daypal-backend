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

import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.repository.MealRepository;
import com.bezkoder.spring.security.postgresql.security.services.MealService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MealController {

	@Autowired
	private MealService mealService;
	@Autowired
	private MealRepository mealRepository;

	// pasarle todos los meals que existan
	@GetMapping(value = "/meals")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Meal> getMeals() {
		List<Meal> meals = (List<Meal>) this.mealService.findAll();
		return meals;
	}

	// eliminar un meal
	@DeleteMapping("/meals/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteMeal(@PathVariable("id") long id) {
		try {
			mealRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
