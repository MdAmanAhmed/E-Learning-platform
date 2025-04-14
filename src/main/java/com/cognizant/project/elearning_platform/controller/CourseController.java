package com.cognizant.project.elearning_platform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.CourseRequestDTO;
import com.cognizant.project.elearning_platform.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	CourseService courseService;
	
	@GetMapping("/")
	public ResponseEntity<List<CourseRequestDTO>> viewAllCourse(){
			
			return new ResponseEntity<>(courseService.viewAllCourse(),HttpStatus.OK);
		}
	
	
	@GetMapping("/instructor/{instructorId}")
	public ResponseEntity<List<CourseRequestDTO>> viewAllCourse(@PathVariable int instructorId){
		
		return new ResponseEntity<>(courseService.viewAllCourse(instructorId),HttpStatus.OK);
	}
	
}
