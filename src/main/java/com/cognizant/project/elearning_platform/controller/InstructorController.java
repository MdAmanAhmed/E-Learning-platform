package com.cognizant.project.elearning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.InstructorDTO;
import com.cognizant.project.elearning_platform.service.InstructorService;

@RestController
public class InstructorController {
	@Autowired
	InstructorService instructorService;
	
	@PostMapping("/addInstructor")
	public ResponseEntity<InstructorDTO> addInstructor(InstructorDTO instructor){
		return new ResponseEntity<>(instructorService.addInstructor(instructor),HttpStatus.OK);
	}
	


}
