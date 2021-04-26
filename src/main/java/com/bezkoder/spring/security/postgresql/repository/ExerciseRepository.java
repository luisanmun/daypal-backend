package com.bezkoder.spring.security.postgresql.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.postgresql.models.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
