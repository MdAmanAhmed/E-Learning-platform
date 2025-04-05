package com.cognizant.project.elearning_platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cognizant.project.elearning_platform.exception.AllException.*;

@ControllerAdvice
public class GolbalExceptionHandler {

	@ExceptionHandler(InstructorDetailNotFound.class)
	public ResponseEntity<String> handleInstructorDetailNotFound(){
		return new ResponseEntity("Instructor Details Not Found\n REGISTER IF U R NEW USER",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(StudentDetailNotFound.class)
	public ResponseEntity<String> handleStudentDetailNotFound(){
		return new ResponseEntity("Student Details Not Found",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidCourse.class)
	public ResponseEntity<String> handleInvalidCourse(){
		return new ResponseEntity("Invalid course",HttpStatus.NOT_FOUND);
	}
	
}
