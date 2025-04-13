package com.cognizant.project.elearning_platform.dto;


import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class EnrollmentResponseDTO {
	private int enrollmentId;
	private int studentId;
	private int courseId;
	private int progress;
private String courseTitle;
private String instructorName;

	
}