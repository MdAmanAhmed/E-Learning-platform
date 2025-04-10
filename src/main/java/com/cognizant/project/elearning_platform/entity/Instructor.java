package com.cognizant.project.elearning_platform.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
public class Instructor extends User{
	@Column(nullable=false)
	private String salary;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="instructorId")
	@JsonIgnore
	// @JsonManagedReference
	private List<Course> course;
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
