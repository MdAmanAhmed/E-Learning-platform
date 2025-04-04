package com.cognizant.project.elearning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.StudentDTO;
import com.cognizant.project.elearning_platform.service.StudentService;

@RestController
public class StudentController {
	@Autowired
StudentService studentService;
	
	@PostMapping("addStudent")
	public ResponseEntity<StudentDTO> addStudent(StudentDTO studentDTO){
		return new ResponseEntity<>(studentService.addStudent(studentDTO),HttpStatus.OK);
	}
}
