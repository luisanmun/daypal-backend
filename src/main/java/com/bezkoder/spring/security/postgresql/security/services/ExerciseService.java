package com.bezkoder.spring.security.postgresql.security.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.postgresql.models.Exercise;
import com.bezkoder.spring.security.postgresql.repository.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Transactional
	public Collection<Exercise> findAll() {
		return this.exerciseRepository.findAll();
	}
}
