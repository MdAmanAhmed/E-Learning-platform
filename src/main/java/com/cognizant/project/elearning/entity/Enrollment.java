package com.cognizant.project.elearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enrollment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int enrollmentId;
	@ManyToOne
	@JoinColumn(name="studentId")
	private Student studentId;
	@ManyToOne
	@JoinColumn(name="courseId")
	private Course courseId;

	private int progress;
}
