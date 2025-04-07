package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Instructor extends User{
	private String salary;
}

























/*@Entity
@Data
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int instructorId;
	private String name;
	private String password;
	private String email;
	private String role;
}*/
