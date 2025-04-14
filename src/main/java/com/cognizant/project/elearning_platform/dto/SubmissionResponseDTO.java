package com.cognizant.project.elearning_platform.dto;

import lombok.Data;

@Data
public class SubmissionResponseDTO {
	private int submissionId;
	private int assessmentId;
	private int studentId;
    private String type;
	private int maxScore;
	private int courseId;
	private String title;
	private String instructorName;
}
