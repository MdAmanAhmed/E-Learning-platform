package com.cognizant.project.elearning_platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.project.elearning_platform.exception.AllException.*;

@ControllerAdvice
public class GolbalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(InstructorDetailNotFound.class)
	public ResponseEntity<String> handleInstructorDetailNotFound(){
		return new ResponseEntity<>("Instructor Details Not Found\n register if u r new user",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(StudentDetailNotFound.class)
	public ResponseEntity<String> handleStudentDetailNotFound(){
		return new ResponseEntity<>("Student Details Not Found\n Register if u r new user",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidCourse.class)
	public ResponseEntity<String> handleInvalidCourse(){
		return new ResponseEntity("Invalid course",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AlreadyEnrolled.class)
	public ResponseEntity<String> handleAlreadyEnrolled(){
		return new ResponseEntity<>("User already enrolled in that course",HttpStatus.OK);
	}
	
	@ExceptionHandler(AssessmentNotFound.class)
	public ResponseEntity<String> assessmentNotFound(){
		return new ResponseEntity<>("No such Assessment Conducted check assessment id properly",HttpStatus.OK);
	}
	
}
