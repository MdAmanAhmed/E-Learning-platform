package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Instructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequestDTO {
	
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String contentURL;

}
