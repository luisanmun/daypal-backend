package com.bezkoder.spring.security.postgresql.security.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.Meal;
import com.bezkoder.spring.security.postgresql.repository.MealRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository mealRepository;

	@Transactional
	public Collection<Meal> findAll() {
		return this.mealRepository.findAll();
	}
}
