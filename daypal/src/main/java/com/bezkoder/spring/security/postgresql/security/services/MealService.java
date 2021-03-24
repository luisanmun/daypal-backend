package com.bezkoder.spring.security.postgresql.security.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.MealRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository mealRepository;

	@Transactional
	public Collection<Meal> findAll() {
		return this.mealRepository.findAll();
	}
}
