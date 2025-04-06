package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Student;

import lombok.Data;

@Data
public class SubmissionDTO {
	private int submissionId;
	private Assessment assessmentId;
	private Student studentId;
	private int score;
}