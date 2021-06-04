package com.bezkoder.spring.security.postgresql.security.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bezkoder.spring.security.postgresql.models.EMeal;
import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.Role;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.ExerciseRepository;
import com.bezkoder.spring.security.postgresql.repository.MealRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private MealRepository mealRepository;

	@Autowired
	MealService mealService;

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	ExerciseService exerciseService;

	@Autowired
	private UserRepository userRepository;

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
		}else {//opcion por defecto si se hiciera esta peticion, aunque se va a controlar desde frontend para que no se pueda hacer
			res = u.getSnack();
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
		//ademas de subir el marcador, actualizo la fecha en la que se actualizo
		Integer time = LocalTime.now().getHour();
		Integer currentDateSum = LocalDate.now().getDayOfMonth() + LocalDate.now().getMonthValue() + LocalDate.now().getYear();
		if (time >= 10 && time < 11) {
			u.setLastUpdatedBreakfastDate(currentDateSum);
		} else if (time >= 14 && time < 15) {
			u.setLastUpdatedLunchDate(currentDateSum);
		} else if (time >= 18 && time < 19) {
			u.setLastUpdatedSnackDate(currentDateSum);
		} else if (time >= 22 && time < 23) {
			u.setLastUpdatedDinnerDate(currentDateSum);
		}
		userRepository.save(u);
	}

	@Transactional
	public void exercisesCounterUp(User u) {
		u.setExercisesCounter(u.getExercisesCounter() + 1);
		Integer currentDateSum = LocalDate.now().getDayOfMonth() + LocalDate.now().getMonthValue() + LocalDate.now().getYear();
		u.setLastUpdatedExerciseDate(currentDateSum);
		userRepository.save(u);
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
		// hago aleatorio el orden para que luego al recorrer sea aleatoria la
		// asignacion
		Collections.shuffle(meals);

		Double objective = 25 * u.caloriesTarget() / 100;

		// caso para el usuario recien creado que no tenia ninguna comida previa, es
		// decir esta a null y no se puede quedar con la anterior porque era null
		if (u.getBreakfast() == null) {
			Meal defaultBreakfast = mealRepository.findById(1L).get();
			u.setBreakfast(defaultBreakfast);
		} else {
			for (Meal m : meals) {
				if (!u.getBreakfast().equals(m) && m.getId() != 1L && m.getCategory().equals(EMeal.BREAKFAST)
						&& (m.getCalories() >= objective - 50 && m.getCalories() <= objective + 50)) {
					u.setBreakfast(m);
					break;
				}
			}
		}
		userRepository.save(u);
	}

	// asignador de almuerzos (40% calorias)
	@Transactional
	public void lunchAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		// hago aleatorio el orden para que luego al recorrer sea aleatoria la
		// asignacion
		Collections.shuffle(meals);

		Double objective = 40 * u.caloriesTarget() / 100;
		System.out.println(objective);

		// caso para el usuario recien creado que no tenia ninguna comida previa, es
		// decir esta a null y no se puede quedar con la anterior porque era null
		if (u.getLunch() == null) {
			Meal defaultLunch = mealRepository.findById(2L).get();
			u.setLunch(defaultLunch);
		} else {
			for (Meal m : meals) {
				if (!u.getLunch().equals(m) && m.getId() != 2L && m.getCategory().equals(EMeal.LUNCH)
						&& (m.getCalories() >= objective - 50 && m.getCalories() <= objective + 50)) {
					u.setLunch(m);
					break;
				}
			}
		}
		userRepository.save(u);
	}

	// asignador de meriendas (20% calorias)
	@Transactional
	public void snackAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		// hago aleatorio el orden para que luego al recorrer sea aleatoria la
		// asignacion
		Collections.shuffle(meals);

		Double objective = 20 * u.caloriesTarget() / 100;

		// caso para el usuario recien creado que no tenia ninguna comida previa, es
		// decir esta a null y no se puede quedar con la anterior porque era null
		if (u.getSnack() == null) {
			Meal defaultSnack = mealRepository.findById(3L).get();
			u.setSnack(defaultSnack);
		} else {
			for (Meal m : meals) {
				if (!u.getSnack().equals(m) && m.getId() != 3L && m.getCategory().equals(EMeal.SNACK)
						&& (m.getCalories() >= objective - 50 && m.getCalories() <= objective + 50)) {
					u.setSnack(m);
					break;
				}
			}
		}
		userRepository.save(u);
	}

	// asignador de cenas (15% calorias)
	@Transactional
	public void dinnerAssigner(User u) {
		List<Meal> meals = new ArrayList<Meal>();
		meals.addAll(mealService.findAll());
		// hago aleatorio el orden para que luego al recorrer sea aleatoria la
		// asignacion
		Collections.shuffle(meals);

		Double objective = 15 * u.caloriesTarget() / 100;

		// caso para el usuario recien creado que no tenia ninguna comida previa, es
		// decir esta a null y no se puede quedar con la anterior porque era null
		if (u.getDinner() == null) {
			Meal defaultDinner = mealRepository.findById(4L).get();
			u.setDinner(defaultDinner);
		} else {
			for (Meal m : meals) {
				if (!u.getDinner().equals(m) && m.getId() != 4L && m.getCategory().equals(EMeal.DINNER)
						&& (m.getCalories() >= objective - 50 && m.getCalories() <= objective + 50)) {
					u.setDinner(m);
					break;
				}
			}
		}
		userRepository.save(u);
	}

	// asignador ejercicio cada nuevo dia
	// si el peso esta por encima del ideal pongo ejercicio de perder
	@Transactional
	public void exerciseAssigner(User u) {
		List<Exercise> exercises = new ArrayList<Exercise>();
		exercises.addAll(exerciseService.findAll());
		// hago aleatorio el orden para que luego al recorrer sea aleatoria la
		// asignacion
		Collections.shuffle(exercises);

		if (u.getExercise() == null) {
			Exercise defaultExercise = exerciseRepository.findById(1L).get();
			u.setExercise(defaultExercise);
		} else {
			for (Exercise e : exercises) {
				if (!u.getExercise().equals(e) && e.getId() != 1L) {
					// te pongo ejercicio para subir de peso
					if (u.idealWeight() == -1 && e.getLoseWeight().equals(true)) {
						u.setExercise(e);
						break;
						// no importa el tipo de ejercicio
					} else if (u.idealWeight() == 0) {
						u.setExercise(e);
						break;
						// te pongo ejercicio para baja de peso
					} else if (u.idealWeight() == 1 && e.getLoseWeight().equals(false)) {
						u.setExercise(e);
						break;
					}
				}
			}
		}
		userRepository.save(u);
	}

	// actualizar el peso del usuario
	@Transactional
	public void patchWeight(int weight, User u) {
		u.setWeight(weight);
		userRepository.save(u);
	}

	// check de si un usuario es rol User, ya que se usa mucho y la comprobacion no
	// es obvia
	public Boolean checkIfRoleUser(User u) {
		Boolean res = false;
		Set<String> rolesString = new HashSet<String>();
		for (Role ro : u.getRoles()) {
			rolesString.add(ro.getName().toString());
		}
		if (rolesString.contains("ROLE_USER")) {
			res = true;
		}
		return res;
	}

	// funcion que asocia las propiedades de un usuario recien registrado
	public void firstAssociation(User u) {
		this.breakfastAssigner(u);
		this.lunchAssigner(u);
		this.snackAssigner(u);
		this.dinnerAssigner(u);
		this.exerciseAssigner(u);

	}
}
