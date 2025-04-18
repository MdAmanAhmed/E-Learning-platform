package com.cognizant.project.elearning_platform.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Student extends User{
	@Column(length=50)
	private String college;
	private int age;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="studentId")
	@JsonIgnore
	List<Enrollment> enrollment;

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="studentId")
	@JsonIgnore
	List<Submission> submission;
	
<<<<<<< HEAD
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="studentId")
=======
	@ManyToMany(mappedBy="studentId",cascade=CascadeType.ALL)
>>>>>>> ccedabb400a2ff4c7a4b2be423a682668a744c03
	private List<Notification> notification;
}







