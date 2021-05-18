package com.bezkoder.spring.security.postgresql.security.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.EMeal;
import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.ExerciseRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	MealService mealService;
	
	@Autowired
	ExerciseService exerciseService;

	@Transactional
	public Meal getMealNow(User u) {
		Integer time = LocalTime.now().getHour();
		Meal res = null;
		if (time >= 10 && time < 11) {
			res = u.getBreakfast();
		} else if (time >= 14 && time < 15) {
			res = u.getLunch();
		} else if (time >= 18 && time < 19) {
			res = u.getSnack();
		} else if (time >= 22 && time < 23) {
			res = u.getDinner();
		}
		return res;
	}

	@Transactional
	public Exercise getExerciseNow(User u) {
		return u.getExercise();
	}

	@Transactional
	public void mealsCounterUp(User u) {
		u.setMealsCounter(u.getMealsCounter() + 1);
	}

	@Transactional
	public void exercisesCounterUp(User u) {
		u.setExercisesCounter(u.getExercisesCounter() + 1);
	}

	// devolver puntuacion global del usuario
	@Transactional
	public Integer getScore(User u) {

		Long mealScore = u.getMealsCounter();
		Long exerciseScore = u.getExercisesCounter();
		Long daysSinceRegistration = ChronoUnit.DAYS.between(u.getSignUpDate(), LocalDate.now());

		Long idealScore = daysSinceRegistration * mealScore + daysSinceRegistration * exerciseScore;
		Long actualScore = mealScore + exerciseScore;

		Double tmp = (actualScore.doubleValue() * 100) / idealScore.doubleValue();

		Long res = Math.round(tmp);

		return res.intValue();
	}

	// asignador de desayunos (25% calorias)
	@Transactional
	public void breakfastAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		//hago aleatorio el orden para que luego al recorrer sea aleatoria la asignacion
		Collections.shuffle(meals);
		
		Double objective = 25 * u.caloriesTarget() / 100;
		
		for(Meal m: meals) {
			if(m.getCategory().equals(EMeal.BREAKFAST) && (m.getCalories()>=objective-50 && m.getCalories()<=objective+50)) {
				u.setBreakfast(m);
				break;
			}
		}
		
	}

	// asignador de almuerzos (40% calorias)
	@Transactional
	public void lunchAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		//hago aleatorio el orden para que luego al recorrer sea aleatoria la asignacion
		Collections.shuffle(meals);
		
		Double objective = 40 * u.caloriesTarget() / 100;
		
		for(Meal m: meals) {
			if(m.getCategory().equals(EMeal.BREAKFAST) && (m.getCalories()>=objective-50 && m.getCalories()<=objective+50)) {
				u.setBreakfast(m);
				break;
			}
		}

	}

	// asignador de meriendas (20% calorias)
	@Transactional
	public void snackAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		//hago aleatorio el orden para que luego al recorrer sea aleatoria la asignacion
		Collections.shuffle(meals);
		
		Double objective = 20 * u.caloriesTarget() / 100;
		
		for(Meal m: meals) {
			if(m.getCategory().equals(EMeal.BREAKFAST) && (m.getCalories()>=objective-50 && m.getCalories()<=objective+50)) {
				u.setBreakfast(m);
				break;
			}
		}

	}

	// asignador de cenas (15% calorias)
	@Transactional
	public void dinnerAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		//hago aleatorio el orden para que luego al recorrer sea aleatoria la asignacion
		Collections.shuffle(meals);
		
		Double objective = 15 * u.caloriesTarget() / 100;
		
		for(Meal m: meals) {
			if(m.getCategory().equals(EMeal.BREAKFAST) && (m.getCalories()>=objective-50 && m.getCalories()<=objective+50)) {
				u.setBreakfast(m);
				break;
			}
		}

	}

	// asignador ejercicio cada nuevo dia
	//si el peso esta por encima del ideal pongo ejercicio de perder
	@Transactional
	public void exerciseAssigner(User u) {
		List<Exercise> exercises = new ArrayList<Exercise>();
		exercises.addAll(exerciseService.findAll());
		//hago aleatorio el orden para que luego al recorrer sea aleatoria la asignacion
		Collections.shuffle(exercises);
				
		for(Exercise e: exercises) {
			//te pongo ejercicio para subir de peso
			if(u.idealWeight()==-1 && e.getLoseWeight().equals(true)) {
				u.setExercise(e);
				break;
			//no importa el tipo de ejercicio
			}else if(u.idealWeight()==0 ) {
				u.setExercise(e);
				break;
			//te pongo ejercicio para baja de peso
			}else if(u.idealWeight()==1 && e.getLoseWeight().equals(false)) {
				u.setExercise(e);
				break;
			}
		}
	}
	
	//actualizar el peso del usuario
	@Transactional
	public void patchWeight(int weight, User u) {
		u.setWeight(weight);
	}
}
