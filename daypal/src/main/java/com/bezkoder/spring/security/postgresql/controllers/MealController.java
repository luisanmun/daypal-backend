package com.bezkoder.spring.security.postgresql.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.security.services.MealService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MealController {
	
//	private final MealService mealService;
//
//	@Autowired
//	public MealController(final MealService mealService) {
//		this.mealService = mealService;
//	}
	
	@Autowired
	private MealService mealService;
	
	//pasarle todos los meals que existan
	@GetMapping(value = "/meals/all")
	@PreAuthorize("hasRole('ADMIN')")
	public String getMeals(Model model) {
		Iterable<Meal> meals = this.mealService.findAll();
		model.addAttribute("meals", meals);
		return "Todas las comidas en el sistema";
	}
}
