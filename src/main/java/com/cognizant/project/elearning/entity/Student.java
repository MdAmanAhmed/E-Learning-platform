package com.cognizant.project.elearning.entity;


import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
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
//	@JsonIgnore
	List<Enrollment> enrollment;

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="studentId")
//	@JsonIgnore
	List<Submission> submission;
	

	@ManyToMany(mappedBy="studentId",cascade=CascadeType.ALL)
	private List<Notification> notification;
}







