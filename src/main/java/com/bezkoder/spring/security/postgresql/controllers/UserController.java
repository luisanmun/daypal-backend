package com.bezkoder.spring.security.postgresql.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import com.bezkoder.spring.security.postgresql.security.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	// devolver comida que toque hoy segun la hora
	@GetMapping(value = "/user/{id}/meals")
	@PreAuthorize("hasRole('USER')")
	public Meal getMealNow(@PathVariable("id") final int id) {
		Long userId = Long.valueOf(id);
		User currentUser = userRepository.findById(userId).get();
		return userService.getMealNow(currentUser);
	}

//	// devolver ejercicio que toque hoy
//	@GetMapping(value = "/user/{id}/exercises")
//	@PreAuthorize("hasRole('USER')")
//	public Exercise getExerciseNow() {
//		return userService.getExerciseNow(currentUser);
//	}
//
//	//subir contador comidas del usuario
//	@PatchMapping(value = "/user/{id}/mealsCounter")
//	@PreAuthorize("hasRole('USER')")
//	public void mealsCounterUp() {
//		userService.mealsCounterUp(currentUser);
//	}
//
//	//subir contador ejercicios del usuario
//	@PatchMapping(value = "/user/{id}/exercisesCounter")
//	@PreAuthorize("hasRole('USER')")
//	public void exercisesCounterUp() {
//		userService.mealsCounterUp(currentUser);
//	}
//
//	//obtener la puntacion de un usuario
//	@GetMapping(value = "/user/{id}/score")
//	@PreAuthorize("hasRole('USER')")
//	public Integer getScore() {
//		return userService.getScore(currentUser);
//	}
//
//	// funcion que refresca la comida que toque en esa hora
//	@PatchMapping(value = "/user/{id}/meals")
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
//	@PatchMapping(value = "/user/{id}/exercises")
//	@PreAuthorize("hasRole('USER')")
//	public void refreshExercise() {
//		userService.exerciseAssigner(currentUser);
//	}

	// funcion que refresca elejercicio de ese dia
//	@PatchMapping(value = "/user/{id}/weight")
//	@PreAuthorize("hasRole('USER')")
//	public void patchWeight(@RequestBody int weight, @PathVariable("id") final int id) {
//		Long userId = Long.valueOf(id);
//		User currentUser = userRepository.findById(userId).get();
//		userService.patchWeight(weight, currentUser);
//	}

	@PatchMapping(value = "/user/{id}/weight")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Integer> patchWeight(@RequestBody int weight, @PathVariable("id") final int id) {
		Long userId = Long.valueOf(id);
		User currentUser = userRepository.findById(userId).get();
		try {
			userService.patchWeight(weight, currentUser);
			return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// obtener el peso de un usuario
	@GetMapping(value = "/user/{id}/weight")
	@PreAuthorize("hasRole('USER')")
	public Integer getWeight(@PathVariable("id") final int id) {
		Long userId = Long.valueOf(id);
		User currentUser = userRepository.findById(userId).get();
		return currentUser.getWeight();
	}

	// obtener la altura de un usuario
	@GetMapping(value = "/user/{id}/height")
	@PreAuthorize("hasRole('USER')")
	public Integer getHeight(@PathVariable("id") final int id) {
		Long userId = Long.valueOf(id);
		User currentUser = userRepository.findById(userId).get();
		return currentUser.getHeight();
	}

}
