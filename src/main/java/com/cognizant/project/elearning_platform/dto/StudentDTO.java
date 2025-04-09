package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDTO {
	//private int StudentId;
	@NotBlank(message="student name cant be blank")
	private String name;
	@Size(min=4,message="password should be atlest 4 characters")
	private String password;
	@Email(message="give valid email")
	private String email;
	@NotNull(message="role cant be null")
	private Role role;
	@NotBlank(message="college name shouldnt be blank")
	private String college;
	@Min(value=18,message="age should be >=18")
	private int age;
}
