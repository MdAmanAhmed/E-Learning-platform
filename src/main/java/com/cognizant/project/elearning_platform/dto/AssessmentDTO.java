package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//@Data
public class AssessmentDTO {
	private int assessmentId;
	
	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	@NotBlank(message="type field cannot be blank")
	private String type;
	
	private int maxScore;
	
	@NotNull(message="courseId field cannot be empty")
	private Course courseId;
	
	
}
