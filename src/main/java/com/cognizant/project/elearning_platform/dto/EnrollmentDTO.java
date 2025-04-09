package com.cognizant.project.elearning_platform.dto;


import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class EnrollmentDTO {
	private int enrollmentId;
	@NotNull(message="studentId cant be null")
	private Student studentId;
	@NotNull(message="courseId cant be null")
	private Course courseId;
	
	private int progress;
}