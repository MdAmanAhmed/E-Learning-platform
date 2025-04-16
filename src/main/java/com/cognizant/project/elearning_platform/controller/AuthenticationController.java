package com.cognizant.project.elearning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.LoginRequestDTO;
import com.cognizant.project.elearning_platform.dto.LoginResponseDTO;
import com.cognizant.project.elearning_platform.dto.RegisterRequestDTO;
import com.cognizant.project.elearning_platform.dto.RegisterResponseDTO;
import com.cognizant.project.elearning_platform.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
return new ResponseEntity<>(authenticationService.register(registerRequestDTO),HttpStatus.OK);
	
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
return new ResponseEntity<>(authenticationService.login(loginRequestDTO),HttpStatus.OK);
	}
}
