package com.cognizant.project.elearning.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequestDTO  {
	
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String contentURL;

}
