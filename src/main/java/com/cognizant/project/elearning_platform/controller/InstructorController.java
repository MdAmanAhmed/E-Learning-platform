package com.cognizant.project.elearning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.AssessmentDTO;
import com.cognizant.project.elearning_platform.dto.CourseDTO;
import com.cognizant.project.elearning_platform.dto.InstructorDTO;
import com.cognizant.project.elearning_platform.service.AssessmentService;
import com.cognizant.project.elearning_platform.service.CourseService;
import com.cognizant.project.elearning_platform.service.InstructorService;

@RestController
public class InstructorController {
	@Autowired
	InstructorService instructorService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	AssessmentService assessmentService;
	
	@PostMapping("/addInstructor")
	public ResponseEntity<InstructorDTO> addInstructor(InstructorDTO instructorDTO){
		return new ResponseEntity<>(instructorService.addInstructor(instructorDTO),HttpStatus.OK);
	}
	@PostMapping("/addCourse/{instructorId}")
	public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO,@PathVariable("instructorId") int instructorId){
		
		return new ResponseEntity<>(courseService.addCourse(courseDTO,instructorId),HttpStatus.OK);
	}
	@PostMapping("/createAssessment/{courseId}")
	public ResponseEntity<AssessmentDTO> createAssessment(@RequestBody AssessmentDTO assessmentDTO,@PathVariable("courseId") int courseId){
		
		return new ResponseEntity<>(assessmentService.createAssessment(assessmentDTO,courseId),HttpStatus.OK);
	}

}
