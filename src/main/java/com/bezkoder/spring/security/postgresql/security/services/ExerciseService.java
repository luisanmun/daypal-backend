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

@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Transactional
	public Collection<Exercise> findAll() {
		return this.exerciseRepository.findAll();
	}
	
//	@Transactional
//	public void deleteBreakfast(long id) {
//		//Proteccion para que no se elimine de la bd el desayuno por defecto
//		if(id != 1L) {
//			Meal mealToDelete = mealRepository.findById(id).get();
//			List<User> allUsers = userRepository.findAll();
//			Meal defaultBreakfast = mealRepository.findById(1L).get();
//			
//			for(User u : allUsers) {
//				if(userService.checkIfRoleUser(u)) {	//solo miro los users
//					if(u.getBreakfast().equals(mealToDelete)) {
//						u.setBreakfast(defaultBreakfast);
//						userRepository.save(u);
//					}
//				}
//			}
//					
//			mealRepository.deleteById(id);
//		}
//	}
}
