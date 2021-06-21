package com.bezkoder.spring.security.postgresql.security.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.ExerciseRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Transactional
	public Collection<Exercise> findAll() {
		return this.exerciseRepository.findAll();
	}
	
	@Transactional
	public void deleteExercise(long id) {
		//Proteccion para que no se elimine de la bd el desayuno por defecto
		if(id != 1L) {
			Exercise exerciseToDelete = exerciseRepository.findById(id).get();
			List<User> allUsers = userRepository.findAll();
			Exercise defaultExercise = exerciseRepository.findById(1L).get();
			
			for(User u : allUsers) {
				if(userService.checkIfRoleUser(u)) {	//solo miro los users
					if(u.getExercise().equals(exerciseToDelete)) {
						u.setExercise(defaultExercise);
						userRepository.save(u);
					}
				}
			}
					
			exerciseRepository.deleteById(id);
		}
	}
}
