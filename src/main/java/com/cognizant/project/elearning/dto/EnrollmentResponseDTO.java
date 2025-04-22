package com.cognizant.project.elearning.dto;

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