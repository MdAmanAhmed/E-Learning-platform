package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstructorDTO {
	//private int instructorId;
	@NotBlank(message="give instructor name")
	private String name;
	@Size(min=4,message="password should be atleast 4 characters")
	private String password;
	@Email
	private String email;
	@NotNull(message="role cannot be null")
	private Role role;
	@NotBlank(message="salary cant be blank")
	private String salary;
}
