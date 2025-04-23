package com.cognizant.project.elearning.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int courseId;
	@Column(nullable=false,length=50)
	private String title;
	@Column(nullable=false,length=50)
	private String description;
	@Column(nullable=false)
	private String contentURL;

	@ManyToOne 
	@JoinColumn(name="instructorId")
	private Instructor instructorId;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="courseId",orphanRemoval=true)
	private List<Enrollment> enrollment;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="courseId",orphanRemoval=true)
	private List<Assessment> assessment;
	
	@OneToMany(mappedBy="courseId",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Notification> notification;
	
}
