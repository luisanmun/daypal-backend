package com.bezkoder.spring.security.postgresql.security.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.ExerciseRepository;

@Service
public class UserService {

	// @Transactional
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

	public Exercise getExerciseNow(User u) {
		return u.getExercise();
	}

	public void mealsCounterUp(User u) {
		u.setMealsCounter(u.getMealsCounter() + 1);
	}

	public void exercisesCounterUp(User u) {
		u.setExercisesCounter(u.getExercisesCounter() + 1);
	}

	// devolver puntuacion global del usuario
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
	public void breakfastAssigner(User u) {

		Double baseMetaMale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) + 5;
		Double baseMetaFemale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) - 161;
	}

	// asignador de almuerzos (40% calorias)
	public void lunchAssigner(User u) {
		Double baseMetaMale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) + 5;
		Double baseMetaFemale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) - 161;

	}

	// asignador de meriendas (20% calorias)
	public void snackAssigner(User u) {
		Double baseMetaMale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) + 5;
		Double baseMetaFemale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) - 161;

	}

	// asignador de cenas (15% calorias)
	public void dinnerAssigner(User u) {
		Double baseMetaMale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) + 5;
		Double baseMetaFemale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) - 161;

	}

	// asignador ejercicio cada nuevo dia
	public void exerciseAssigner(User u) {
		Double baseMetaMale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) + 5;
		Double baseMetaFemale = (10*u.getWeight()) + (6.25*u.getHeight()) - (5*42.5) - 161;

	}
}
