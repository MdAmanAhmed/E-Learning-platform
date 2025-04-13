package com.cognizant.project.elearning_platform.dto;

import lombok.Data;

@Data
public class CourseResponseDTO {
    private int courseId;
	private String title;
	private String description;
	private String contentURL;
	private int instructorId;
	private String instructorName;
}
