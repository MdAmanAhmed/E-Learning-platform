package com.cognizant.project.elearning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.CourseDTO;
import com.cognizant.project.elearning_platform.dto.InstructorDTO;
import com.cognizant.project.elearning_platform.service.CourseService;
import com.cognizant.project.elearning_platform.service.InstructorService;

@RestController
public class InstructorController {
	@Autowired
	InstructorService instructorService;
	
	@Autowired
	CourseService courseService;
	
	@PostMapping("/addInstructor")
	public ResponseEntity<InstructorDTO> addInstructor(InstructorDTO instructor){
		return new ResponseEntity<>(instructorService.addInstructor(instructor),HttpStatus.OK);
	}
	@PostMapping("/addCourse/{instuctor_id}")
	public ResponseEntity<CourseDTO> addCourse(CourseDTO courseDTO,@PathVariable int instructor_id){
		return new ResponseEntity<>(courseService.addCourse(courseDTO,instructor_id),HttpStatus.OK);
	}

}
