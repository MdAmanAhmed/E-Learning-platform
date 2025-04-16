package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Course;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AssessmentRequestDTO {
	private int assessmentId;
	
	

	@NotBlank
	private String type;
	@Min(1)
	private int maxScore;
	
//	@NotNull
//	private Course courseId;
	
	
}
