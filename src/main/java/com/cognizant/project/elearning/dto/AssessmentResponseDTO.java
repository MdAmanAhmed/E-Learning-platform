package com.cognizant.project.elearning.dto;

import lombok.Data;

@Data
public class AssessmentResponseDTO {
	
	private String type;
	private int maxScore;
	private int courseId;
	private String title;
	private String contentURL;
	private int instructorId;
	private String instructorName;
}
