package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Course;
import lombok.Data;
@Data
public class AssessmentDTO {
	private int assessmentId;
	private String type;
	private int maxScore;
	private Course courseId;
	
	
}
