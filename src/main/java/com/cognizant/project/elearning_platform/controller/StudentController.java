package com.cognizant.project.elearning_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.CourseDTO;
import com.cognizant.project.elearning_platform.dto.EnrollmentDTO;
import com.cognizant.project.elearning_platform.dto.StudentDTO;
import com.cognizant.project.elearning_platform.dto.SubmissionDTO;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.service.CourseService;
import com.cognizant.project.elearning_platform.service.EnrollmentService;
import com.cognizant.project.elearning_platform.service.StudentService;
import com.cognizant.project.elearning_platform.service.SubmissionService;

@RestController
public class StudentController {
	@Autowired
StudentService studentService;
	
	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	SubmissionService submissionService;
	
	@Autowired
	CourseService courseService;
	
	@PostMapping("/addStudent")
	public ResponseEntity<StudentDTO> addStudent(StudentDTO studentDTO){
		return new ResponseEntity<>(studentService.addStudent(studentDTO),HttpStatus.OK);
	}
	
	@PostMapping("/enroll/{studentId}/{courseId}")
	public ResponseEntity<EnrollmentDTO> enroll(@PathVariable int studentId,@PathVariable int courseId,EnrollmentDTO enrollmentDTO){
		return new ResponseEntity<>(enrollmentService.enroll(studentId,courseId,enrollmentDTO),HttpStatus.OK);
	}
	
	
	@PostMapping("/submitAssessment/{studentId}/{assessmentId}")
	public ResponseEntity<SubmissionDTO> submitAssessment(SubmissionDTO submissionDTO,
			@PathVariable int studentId,@PathVariable int assessmentId){
		
		

		return new ResponseEntity<>(submissionService.submitAssessment(submissionDTO,studentId,assessmentId),HttpStatus.OK);
	}
	
	@GetMapping("/viewStudent/{studentId}")
	public ResponseEntity<StudentDTO> viewStudent(@PathVariable int studentId){
		
		return new ResponseEntity<>(studentService.viewStudent(studentId),HttpStatus.OK);
	}
	
	
	@GetMapping("/viewAllCourse")
public ResponseEntity<List<CourseDTO>> viewAllCourse(){
		
		return new ResponseEntity<>(courseService.viewAllCourse(),HttpStatus.OK);
	}
	
	@GetMapping("/viewEnrolled/{studentId}")
	public ResponseEntity<List<Course>> viewEnrolled(@PathVariable int studentId){
		return new ResponseEntity<>(enrollmentService.viewEnrolled(studentId),HttpStatus.OK);
		
	}
	

	
	@PostMapping("/login")
	public String login(Student student) {
		
		return studentService.verify(student);
	}
	
	
	
	
	
}
