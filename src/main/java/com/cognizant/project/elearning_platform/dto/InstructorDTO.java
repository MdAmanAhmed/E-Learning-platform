package com.cognizant.project.elearning_platform.dto;

import lombok.Data;

@Data
public class InstructorDTO {
	private int instructorId;
	private String name;
	private String password;
	private String email;
	private String role;
}
