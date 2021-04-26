package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "exercises")

public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 20)
	private String title;

	@NotBlank
	@Size(max = 500)
	private String description;

	@Size(min = 0, max = 3000)
	private Integer calories;

	@Column(name = "lose_weight")
	@NotBlank
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
