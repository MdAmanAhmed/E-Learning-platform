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
import com.cognizant.project.elearning_platform.dto.EnrollmentResponseDTO;
import com.cognizant.project.elearning_platform.dto.InstructorResponseDTO;

import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.service.AssessmentService;

import com.cognizant.project.elearning_platform.service.CourseService;
import com.cognizant.project.elearning_platform.service.EnrollmentService;
import com.cognizant.project.elearning_platform.service.InstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
	@Autowired
	InstructorService instructorService;
	
	@Autowired
	 EnrollmentService  enrollmentService;
	@Autowired
	CourseService courseService;
	
	@Autowired
	AssessmentService assessmentService;


	@PreAuthorize("#instructorId==authentication.principal.id")
	@PostMapping("/{instructorId}/courses")

	public ResponseEntity<CourseResponseDTO> addCourse(@PathVariable int instructorId, @Valid @RequestBody CourseRequestDTO courseDTO){

		return new ResponseEntity<>(courseService.addCourse(courseDTO,instructorId),HttpStatus.OK);

	}
	
	
	
	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@PostMapping("/{instructorId}/courses/{courseId}/assessments")
	public ResponseEntity<AssessmentResponseDTO> createAssessment( @Valid @RequestBody AssessmentRequestDTO assessmentRequestDTO,@PathVariable("courseId") int courseId){
		
	return new ResponseEntity<>(assessmentService.createAssessment(assessmentRequestDTO,courseId),HttpStatus.OK);
	}
	

	
	@PreAuthorize("#instructorId==authentication.principal.id")
	@PutMapping("{instructorId}/courses/{courseId}")
	public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int instructorId,@PathVariable int courseId,@Valid @RequestBody CourseRequestDTO courseRequestDTO){
		
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
	@PreAuthorize("#instructorId==authentication.principal.id")
@GetMapping("/{instructorId}/course/{courseId}/enrolls")
	public ResponseEntity<List<EnrollmentResponseDTO>> enrolledStudents(int courseId){
		return new ResponseEntity<>(enrollmentService.enrolledStudents(courseId),HttpStatus.OK);
	}
	
}
