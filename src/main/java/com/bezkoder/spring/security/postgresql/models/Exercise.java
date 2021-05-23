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
	@Size(max = 40)
	private String title;

	@NotBlank
	@Size(min=3)
	@Size(max = 500)
	private String description;

	@Column(name = "lose_weight")
	@NotNull
	//true es ejercicio para bajar de peso, false para subir de peso
	private boolean loseWeight;

	// -------------------------------------------------

	public Exercise(String title, String description, Boolean loseWeight) {
		this.title = title;
		this.description = description;
		this.loseWeight = loseWeight;
	}

	public Exercise() {
		this.title = "";
		this.description = "";
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

	public Boolean getLoseWeight() {
		return loseWeight;
	}

	public void setLoseWeight(Boolean loseWeight) {
		this.loseWeight = loseWeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (loseWeight ? 1231 : 1237);
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
		Exercise other = (Exercise) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loseWeight != other.loseWeight)
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
		return "Exercise [id=" + id + ", title=" + title + ", description=" + description + ", loseWeight=" + loseWeight
				+ "]";
	}
}
