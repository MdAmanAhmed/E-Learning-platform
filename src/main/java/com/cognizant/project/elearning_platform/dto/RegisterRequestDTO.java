package com.cognizant.project.elearning_platform.dto;

import com.cognizant.project.elearning_platform.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {

	@NotBlank
	@Size(min=4,max=50,message="Name must be 4 to 50 characters")
	//if we keep only @Size it will also work . but it allows '   ' as a name(empty or blank)
	//so we kept not blank on top
	private String name;
	@NotBlank
	@Size(min=4,message="Password should be atleast 4 characters long")
	private String password;
	
	@NotBlank
	@Email
//@Pattern(regexp="^[a-z][a-z0-9]*@gmail\\.com",message="invalid email")
	private String email;
@NotNull
	private Role role;
}
