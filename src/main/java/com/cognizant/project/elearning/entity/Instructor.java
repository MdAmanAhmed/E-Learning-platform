package com.cognizant.project.elearning.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Instructor extends User{
		private int salary;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="instructorId")
	@JsonIgnore
	// @JsonManagedReference
	private List<Course> course;
	
	@OneToMany(mappedBy="instructorId" ,cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Notification> notification;
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
