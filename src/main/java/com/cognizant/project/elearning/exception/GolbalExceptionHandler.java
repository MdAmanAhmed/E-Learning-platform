package com.cognizant.project.elearning.exception;

import java.util.HashMap;
import java.util.Map;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.cognizant.project.elearning.exception.AllException.*;

@ControllerAdvice
public class GolbalExceptionHandler{

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Map<String,Object>> handleNoHandlerFoundException(NoHandlerFoundException ex){
		Map<String,Object> error=new HashMap<>();
		error.put("status",String.valueOf(404)+" (Resource Not Found)");
		error.put("message","The URL you are trying to access doesnt exist");

		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler( UserNotExist.class)
	public ResponseEntity<String> handleUserNotExist(UserNotExist ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
		errors.put(error.getField(), error.getDefaultMessage()) );
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String,String>> handleTypeMismatch(
			MethodArgumentTypeMismatchException ex){
		Map<String,String> error=new HashMap<>();
		error.put("error","Invalid path variable");
		error.put("message","Failed to convert "+ex.getValue()+" to the required type "+ex.getName());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InstructorDetailNotFound.class)
	public ResponseEntity<Map<String,String>> handleInstructorDetailNotFound(InstructorDetailNotFound ex){
		Map<String, String> error = new HashMap<>();
		error.put("error","Invalid Details");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StudentDetailNotFound.class)
	public ResponseEntity<Map<String,String>> handleStudentDetailNotFound(StudentDetailNotFound ex){
		Map<String, String> error = new HashMap<>();
		error.put("error","Invalid Details");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidCourse.class)
	public ResponseEntity<Map<String,String>> handleInvalidCourse(InvalidCourse ex){
		Map<String,String> error=new HashMap<>();
		error.put("error","Invalid Course");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AlreadyEnrolled.class)
	public ResponseEntity<String> handleAlreadyEnrolled(){
		return new ResponseEntity<>("User already enrolled in that course",HttpStatus.OK);
	}
	
	@ExceptionHandler(AssessmentNotFound.class)
	public ResponseEntity<String> assessmentNotFound(){
		return new ResponseEntity<>("No such Assessment Conducted check assessment id properly",HttpStatus.OK);
	}
	

	@ExceptionHandler(EmailAlreadyRegistered.class)
	public ResponseEntity<String> handleEmailAlreadyRegistered(){
		String message = "The email address you entered is already registered. Please use a different email address or log in.";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserDetailMismatch.class)
	public ResponseEntity<String> handleUserDetailMismatch(){
		String message = "User details are incorect. Check your email and password";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TokenMismatch.class)
	public ResponseEntity<String> handleTokenMismatch(){
		String message = "Enter correct Token";
		return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(AccessRestricted.class)
	public ResponseEntity<String> handleAccessRestricted(){
		String message = "No access to delete the course";
		return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
	}
	

}

