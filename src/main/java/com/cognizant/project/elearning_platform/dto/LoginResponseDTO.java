package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Role;

import lombok.Data;

@Data
public class LoginResponseDTO {
	private int userId;
	private String name;
	private String password;
	private String email;
	private Role role;
	private String token;
}
