package com.cognizant.project.elearning.dto;


import lombok.Data;

@Data
public class StudentResponseDTO {
	
	private int userId;
	private String name;
	private String password;
	private String email;
	private String role;
	private String college;
	private int age;

}
