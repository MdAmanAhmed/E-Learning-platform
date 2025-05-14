package com.cognizant.project.elearning.dto;

import lombok.Data;

@Data
public class SubmissionResponseDTO {
	private int submissionId;
	private int assessmentId;
    private String question;
	private int maxScore;
	private String title;
	private String answer;
	private String currentScore;
	private int studentId;
}
