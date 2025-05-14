package com.cognizant.project.elearning.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssessmentRequestDTO {
	
	@NotBlank
	private String question;
	
	@Min(1)
	private int maxScore;
	
	
}
