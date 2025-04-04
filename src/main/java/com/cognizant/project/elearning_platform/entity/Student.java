package com.cognizant.project.elearning_platform.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Student  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int StudentId;
	private String name;
	private String password;
	private String email;
	private String role;
}