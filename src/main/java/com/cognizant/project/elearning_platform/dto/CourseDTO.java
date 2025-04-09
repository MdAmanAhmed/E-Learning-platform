package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Instructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseDTO {
	//private int courseId;
	@NotBlank(message="title cannot be null")
	private String title;
	@NotBlank(message="give some description for course")
	private String description;
	@NotBlank(message="give the url")
	private String contentURL;
	@NotNull(message="give instructorId")
	private Instructor instructorId;
}
