package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Instructor;

import lombok.Data;

@Data
public class CourseDTO {
	private int courseId;
	private String title;
	private String description;
	private String contentURL;
	private Instructor instructorId;
}
