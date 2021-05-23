package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "meals")

public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min=3)
	@Size(max = 40)
	private String title;
	
	@NotBlank
	@Size(min=3)
	@Size(max = 500)
	private String description;
	
	@Min(1)
	@Max(5000)
	private Integer calories;
	
	@NotNull
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
	
	public Meal () {
		this.title = "";
		this.description = "";
		this.calories = 0;
		this.category = EMeal.LUNCH;
	}
	//--------------------------------------

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

	public EMeal getCategory() {
		return category;
	}

	public void setCategory(EMeal category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", title=" + title + ", description=" + description + ", calories=" + calories
				+ ", category=" + category + "]";
	}	
	
	
}
