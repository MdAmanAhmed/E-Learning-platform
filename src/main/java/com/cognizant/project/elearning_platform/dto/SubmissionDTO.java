package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubmissionDTO {
	//private int submissionId;
	@NotNull(message="assessmentId cant be null")
	private Assessment assessmentId;
	@NotNull(message="studentId cant be null")
	private Student studentId;
	
	private int score;
}