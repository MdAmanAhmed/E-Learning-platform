package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Enrollment {
	@Id
private int enrollmentId;
	@ManyToOne
private Student studentId;
	@ManyToOne
private Course courseId;
private int progress;
}
