package com.cognizant.project.elearning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.EnrollmentDTO;
import com.cognizant.project.elearning_platform.dto.StudentDTO;
import com.cognizant.project.elearning_platform.service.EnrollmentService;
import com.cognizant.project.elearning_platform.service.StudentService;

@RestController
public class StudentController {
	@Autowired
StudentService studentService;
	
	@Autowired
	EnrollmentService enrollmentService;
	@PostMapping("addStudent")
	public ResponseEntity<StudentDTO> addStudent(StudentDTO studentDTO){
		return new ResponseEntity<>(studentService.addStudent(studentDTO),HttpStatus.OK);
	}
	
	@PostMapping("/enroll/{studentId}/{courseId}")
	public ResponseEntity<EnrollmentDTO> enroll(@PathVariable int studentId,@PathVariable int courseId,EnrollmentDTO enrollmentDTO){
		return new ResponseEntity<>(enrollmentService.enroll(studentId,courseId,enrollmentDTO),HttpStatus.OK);
	}
}
