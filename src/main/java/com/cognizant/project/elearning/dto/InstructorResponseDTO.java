package com.cognizant.project.elearning.dto;

import com.cognizant.project.elearning.entity.Role;

import lombok.Data;

@Data
public class InstructorResponseDTO {
	private int userId;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private Role role;
	
	private String salary;
	
}
