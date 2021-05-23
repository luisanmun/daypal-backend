package com.bezkoder.spring.security.postgresql.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Min(150)
	@Max(220)
	//in cm
	private Integer height;
	
	@Min(40)
	@Max(300)
	//in kg
	private Integer weight;
	
	//female->false, male->true
	private Boolean sex;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@Valid
	@JoinColumn(name = "breakfast_id", referencedColumnName = "id")
	private Meal breakfast;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@Valid
	@JoinColumn(name = "lunch_id", referencedColumnName = "id")
	private Meal lunch;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@Valid
	@JoinColumn(name = "snack_id", referencedColumnName = "id")
	private Meal snack;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@Valid
	@JoinColumn(name = "dinner_id", referencedColumnName = "id")
	private Meal dinner;
	
	private Long mealsCounter;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@Valid
	@JoinColumn(name = "exercise_id", referencedColumnName =  "id")
	private Exercise exercise;
	
	private Long exercisesCounter;
	
	private LocalDate signUpDate;
	
	// -------------------------------------------------
	
	public User() {
	}

	public User(String username, String email, String password, Integer height, Integer weight, Boolean sex) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.breakfast = null;
		this.lunch = null;
		this.snack = null;
		this.dinner = null;
		this.mealsCounter = 0L;
		this.exercise = null;
		this.exercisesCounter = 0L;
		this.signUpDate = LocalDate.now();
		
		//como se esta creando no tiene los roles asignados aqui todavia
		//y la llamada afuera tampoco le podre pasar el id porque al no estar en bd en null todavia
		System.out.println(this.getId());
		
//		Set<String> rolesString = new HashSet<String>();
//		for(Role ro : this.getRoles()) {
//		rolesString.add(ro.getName().toString());
//		}
//		
//		System.out.println(rolesString);
		
		//esto no es asi
//		if(this.getRoles().contains(ERole.ROLE_USER)) {
//			//llamada a funcion asignadora
//		}
	}

	// -------------------------------------------------
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Meal getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Meal breakfast) {
		this.breakfast = breakfast;
	}

	public Meal getLunch() {
		return lunch;
	}

	public void setLunch(Meal lunch) {
		this.lunch = lunch;
	}

	public Meal getSnack() {
		return snack;
	}

	public void setSnack(Meal snack) {
		this.snack = snack;
	}

	public Meal getDinner() {
		return dinner;
	}

	public void setDinner(Meal dinner) {
		this.dinner = dinner;
	}

	public Long getMealsCounter() {
		return mealsCounter;
	}

	public void setMealsCounter(Long mealsCounter) {
		this.mealsCounter = mealsCounter;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Long getExercisesCounter() {
		return exercisesCounter;
	}

	public void setExercisesCounter(Long exercisesCounter) {
		this.exercisesCounter = exercisesCounter;
	}

	public LocalDate getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(LocalDate signUpDate) {
		this.signUpDate = signUpDate;
	}
	
	//Aux
	//calculadora de calorias a consumir en un dia
	public Double caloriesTarget() {
		Double res = 0.0;
		Double baseMetaMale = (10*this.getWeight()) + (6.25*this.getHeight()) - (5*42.5) + 5;
		Double baseMetaFemale = (10*this.getWeight()) + (6.25*this.getHeight()) - (5*42.5) - 161;
		
		if(this.getSex().equals(false)) {
			res = baseMetaFemale;
		}else {
			res = baseMetaMale;
		}
		
		return res;
	}
	//calculadora peso ideal a patir del IMC
	public Integer idealWeight() {
		
		Integer res = null;
		//peso en kg altura en metros
		Double h = this.getHeight()/100.0;
		Double IMC = this.getWeight() / Math.pow(h, 2);
		
		//-1 si estas en bajo peso
		if(IMC < 18.5) {
			res = -1;
		}
		//0 si estas normal, osea peso ideal
		if(IMC >= 18.5 && IMC < 25) {
			res = 0;
		}
		//1 si estas en sobrepeso
		if(IMC >= 25) {
			res = 1;
		}
		return res;
		
	}
	
	
}
