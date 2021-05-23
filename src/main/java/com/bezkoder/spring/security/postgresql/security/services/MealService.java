package com.bezkoder.spring.security.postgresql.security.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.EMeal;
import com.bezkoder.spring.security.postgresql.models.ERole;
import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.Role;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.MealRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Transactional
	public Collection<Meal> findAll() {
		return this.mealRepository.findAll();
	}
	
	@Transactional
	public void deleteBreakfast(long id) {
		//Proteccion para que no se elimine de la bd el desayuno por defecto
		if(id != 1L) {
			Meal mealToDelete = mealRepository.findById(id).get();
			List<User> allUsers = userRepository.findAll();
			Meal defaultBreakfast = mealRepository.findById(1L).get();
			
			for(User u : allUsers) {
				if(userService.checkIfRoleUser(u)) {	//solo miro los users
					if(u.getBreakfast().equals(mealToDelete)) {
						u.setBreakfast(defaultBreakfast);
						userRepository.save(u);
					}
				}
			}
					
			mealRepository.deleteById(id);
		}
	}
	
	@Transactional
	public void deleteLunch(long id) {
		if(id != 2L) {
			Meal mealToDelete = mealRepository.findById(id).get();
			List<User> allUsers = userRepository.findAll();
			Meal defaultLunch = mealRepository.findById(2L).get();
			
			for(User u : allUsers) {
				if(userService.checkIfRoleUser(u)) {	//solo miro los users
					if(u.getLunch().equals(mealToDelete)) {
						u.setLunch(defaultLunch);
						userRepository.save(u);
					}
				}
			}
			
			mealRepository.deleteById(id);
		}
	}
	
	@Transactional
	public void deleteSnack(long id) {
		if(id != 3L) {
			Meal mealToDelete = mealRepository.findById(id).get();
			List<User> allUsers = userRepository.findAll();
			Meal defaultSnack = mealRepository.findById(3L).get();
			
			for(User u : allUsers) {
				if(userService.checkIfRoleUser(u)) {	//solo miro los users
					if(u.getSnack().equals(mealToDelete)) {
						u.setSnack(defaultSnack);
						userRepository.save(u);
					}
				}
			}
					
			mealRepository.deleteById(id);
		}
	}
	
	@Transactional
	public void deleteDinner(long id) {
		if(id != 4L) {
			Meal mealToDelete = mealRepository.findById(id).get();
			List<User> allUsers = userRepository.findAll();
			Meal defaultDinner = mealRepository.findById(4L).get();
			
			for(User u : allUsers) {
				if(userService.checkIfRoleUser(u)) {	//solo miro los users
					if(u.getDinner().equals(mealToDelete)) {
						u.setDinner(defaultDinner);
						userRepository.save(u);
					}
				}
			}
			
			
			mealRepository.deleteById(id);
			
		}
	}
}
