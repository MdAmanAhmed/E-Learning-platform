package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AssessmentDTO {
	//private int assessmentId;
	
	@NotBlank(message="type field cannot be blank")
	private String type;
	
	private int maxScore;
	
	@NotNull(message="courseId field cannot be empty")
	private Course courseId;
	
	
}
