package com.cognizant.project.elearning_platform.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.AssessmentRequestDTO;
import com.cognizant.project.elearning_platform.dto.AssessmentResponseDTO;
import com.cognizant.project.elearning_platform.dto.CourseRequestDTO;
import com.cognizant.project.elearning_platform.dto.CourseResponseDTO;
import com.cognizant.project.elearning_platform.dto.InstructorResponseDTO;

import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.service.AssessmentService;

import com.cognizant.project.elearning_platform.service.CourseService;
import com.cognizant.project.elearning_platform.service.InstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
	@Autowired
	InstructorService instructorService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	AssessmentService assessmentService;
<<<<<<< HEAD
	
	@PostMapping("/addInstructor")
	public ResponseEntity<InstructorDTO> addInstructor(@RequestBody InstructorDTO instructorDTO){
		return new ResponseEntity<>(instructorService.addInstructor(instructorDTO),HttpStatus.OK);
	}
	
	
	@PostMapping("/addCourse/{instructorId}")
	public ResponseEntity<CourseDTO> addCourse(@PathVariable int instructorId,@RequestBody CourseDTO courseDTO){
		// @RequestBody accepts only raw data. but not form data... so we need to make sure wether to use it or not
=======

	@PreAuthorize("#instructorId==authentication.principal.id")
	@PostMapping("/{instructorId}/courses")
	public ResponseEntity<CourseResponseDTO> addCourse(@PathVariable int instructorId, @Valid CourseRequestDTO courseDTO){
>>>>>>> 72df54dcaf045ea0870d7d6814d4587abf8f7543
		return new ResponseEntity<>(courseService.addCourse(courseDTO,instructorId),HttpStatus.OK);
	}
	
	
	
	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@PostMapping("/{instructorId}/courses/{courseId}/assessments")
	public ResponseEntity<AssessmentResponseDTO> createAssessment( @Valid AssessmentRequestDTO assessmentRequestDTO,@PathVariable("courseId") int courseId,
			
			@PathVariable("instructorId") int instructorId){
		
	return new ResponseEntity<>(assessmentService.createAssessment(assessmentRequestDTO,courseId,instructorId),HttpStatus.OK);
	}
	

	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@PutMapping("{instructorId}/courses/{courseId}")
	public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int instructorId,@PathVariable int courseId,@Valid CourseRequestDTO courseRequestDTO){
		
return new ResponseEntity<>(courseService.updateCourse(instructorId, courseId, courseRequestDTO),HttpStatus.ACCEPTED) ;

	}
	
	
	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@DeleteMapping("/{instructorId}")
	public ResponseEntity<String> removeInstructor(@PathVariable int instructorId){
		
		return new ResponseEntity<>(instructorService.removeInstructor(instructorId),HttpStatus.OK);
	}
	
	
	
	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@DeleteMapping("/{instructorId}/courses/{courseId}")
	public ResponseEntity<String> removeCourse(@PathVariable("courseId") int courseId,@PathVariable("instructorId") int instructorId){
		
		return new ResponseEntity<>(courseService.removeCourse(courseId,instructorId),HttpStatus.OK);
	}
	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@GetMapping("/{instructorId}")
	public ResponseEntity<InstructorResponseDTO> viewInstructor(@PathVariable int instructorId){
		
	
	return new ResponseEntity<>(instructorService.viewInstructor(instructorId),HttpStatus.OK);
	}
	

	
	
}
