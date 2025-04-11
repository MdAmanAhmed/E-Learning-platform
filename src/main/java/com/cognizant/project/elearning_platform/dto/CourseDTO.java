package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Instructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//@Data
public class CourseDTO {
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContentURL() {
		return contentURL;
	}
	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}
	public Instructor getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Instructor instructorId) {
		this.instructorId = instructorId;
	}
	private int courseId;
	
	@NotBlank(message="title cannot be null")
	private String title;
	@NotBlank(message="give some description for course")
	private String description;
	@NotBlank(message="give the url")
	private String contentURL;
	@NotNull(message="give instructorId")
	private Instructor instructorId;
}
