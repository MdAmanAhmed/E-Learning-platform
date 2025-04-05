package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Enrollment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int enrollmentId;
	@ManyToOne
	private Student studentId;
	@ManyToOne
	private Course courseId;
	private int progress;
}
