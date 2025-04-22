package com.cognizant.project.elearning.exception;

public class AllException {
	public static class InstructorDetailNotFound extends RuntimeException{
		public InstructorDetailNotFound(String msg){
			super(msg);
		}
	}
	public static class StudentDetailNotFound extends RuntimeException{
		public StudentDetailNotFound(String msg){
			super(msg);
		}
	}
	public static class InvalidCourse extends RuntimeException{
		public InvalidCourse(String msg) {
			super(msg);
		}
	}
	public static class AlreadyEnrolled extends RuntimeException{
		
	}
	public static class AssessmentNotFound extends RuntimeException{
	
	}
	public static class UserNotExist extends RuntimeException{
		public UserNotExist(String msg){
			 super(msg);
		 }
	}
	public static class EmailAlreadyRegistered extends RuntimeException{
		
	}
	public static class TokenMismatch extends RuntimeException{
		
	}
}
