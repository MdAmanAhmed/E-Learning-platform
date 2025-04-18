package com.cognizant.project.elearning_platform.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
=======
>>>>>>> ccedabb400a2ff4c7a4b2be423a682668a744c03
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
	
<<<<<<< HEAD
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="instructorId")
=======
	@OneToMany(mappedBy="instructorId" ,cascade=CascadeType.ALL,orphanRemoval=true)
>>>>>>> ccedabb400a2ff4c7a4b2be423a682668a744c03
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
