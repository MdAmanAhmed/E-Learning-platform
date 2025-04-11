package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

//@Data
public class SubmissionDTO {
	private int submissionId;
	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public Assessment getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Assessment assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@NotNull(message="assessmentId cant be null")
	private Assessment assessmentId;
	@NotNull(message="studentId cant be null")
	private Student studentId;
	
	private int score;
}