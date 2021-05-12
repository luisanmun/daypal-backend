//package com.bezkoder.spring.security.postgresql.controllers;
//
//import java.time.LocalTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bezkoder.spring.security.postgresql.models.Exercise;
//import com.bezkoder.spring.security.postgresql.models.Meal;
//import com.bezkoder.spring.security.postgresql.models.User;
//import com.bezkoder.spring.security.postgresql.security.services.UserService;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//
//	User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//	// devolver meal que toque segun la hora
//	@GetMapping(value = "/todayMeals")
//	@PreAuthorize("hasRole('USER')")
//	public Meal getMealNow() {
//		return userService.getMealNow(currentUser);
//	}
//
//	@GetMapping(value = "/todayExercises")
//	@PreAuthorize("hasRole('USER')")
//	public Exercise getExerciseNow() {
//		return userService.getExerciseNow(currentUser);
//	}
//
//	@PutMapping(value = "/mealsCounter")
//	@PreAuthorize("hasRole('USER')")
//	public void mealsCounterUp() {
//		userService.mealsCounterUp(currentUser);
//	}
//
//	@PutMapping(value = "/exercisesCounter")
//	@PreAuthorize("hasRole('USER')")
//	public void exercisesCounterUp() {
//		userService.mealsCounterUp(currentUser);
//	}
//
//	@GetMapping(value = "/score")
//	@PreAuthorize("hasRole('USER')")
//	public Integer getScore() {
//		return userService.getScore(currentUser);
//	}
//
//	// funcion que refresca la comida que toque en esa hora
//	@PutMapping(value = "/todayMeals")
//	@PreAuthorize("hasRole('USER')")
//	public void refreshMeal() {
//		Integer time = LocalTime.now().getHour();
//		if (time >= 10 && time < 11) {
//			userService.breakfastAssigner(currentUser);
//		} else if (time >= 14 && time < 15) {
//			userService.lunchAssigner(currentUser);
//		} else if (time >= 18 && time < 19) {
//			userService.snackAssigner(currentUser);
//		} else if (time >= 22 && time < 23) {
//			userService.dinnerAssigner(currentUser);
//		}
//	}
//
//	// funcion que refresca elejercicio de ese dia
//	@PutMapping(value = "/todayExercises")
//	@PreAuthorize("hasRole('USER')")
//	public void refreshExercise() {
//		userService.exerciseAssigner(currentUser);
//	}
//
//}
