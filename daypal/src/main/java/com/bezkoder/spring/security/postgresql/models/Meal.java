package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "meals")

public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 20)
	private String title;
	
	@NotBlank
	@Size(max = 500)
	private String description;
	
	@Size(min = 10, max = 3000)
	private Integer calories;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 9)
	private EMeal category;
	
	//--------------------------------------
	
	public Meal (String title, String description, Integer calories, EMeal category) {
		this.title = title;
		this.description = description;
		this.calories = calories;
		this.category = category;
	}
	
	//--------------------------------------

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

	public EMeal getCategory() {
		return category;
	}

	public void setCategory(EMeal category) {
		this.category = category;
	}	
}
