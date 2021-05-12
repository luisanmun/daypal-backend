package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "exercises")

public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min=3)
	@Size(max = 20)
	private String title;

	@NotBlank
	@Size(min=3)
	@Size(max = 500)
	private String description;

	@Min(1)
	@Max(5000)
	private Integer calories;

	@Column(name = "lose_weight")
	@NotNull
	private boolean loseWeight;

	// -------------------------------------------------

	public Exercise(String title, String description, Integer calories, Boolean loseWeight) {
		this.title = title;
		this.description = description;
		this.calories = calories;
		this.loseWeight = loseWeight;
	}

	public Exercise() {
		this.title = "";
		this.description = "";
		this.calories = 0;
		this.loseWeight = false;
	}

	// -------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Boolean getLoseWeight() {
		return loseWeight;
	}

	public void setLoseWeight(Boolean loseWeight) {
		this.loseWeight = loseWeight;
	}
}
