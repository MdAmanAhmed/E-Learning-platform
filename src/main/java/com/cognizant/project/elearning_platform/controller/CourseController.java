package com.cognizant.project.elearning_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.dto.CourseRequestDTO;
import com.cognizant.project.elearning_platform.service.CourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

<<<<<<< HEAD
	@Autowired
	CourseService courseService;
	
	@GetMapping("/")
	public ResponseEntity<List<CourseRequestDTO>> viewAllCourse(){
			
			return new ResponseEntity<>(courseService.viewAllCourse(),HttpStatus.OK);
		}
	
	
	@GetMapping("/instructor/{instructorId}")
	public ResponseEntity<List<CourseRequestDTO>> viewAllCourse(@PathVariable int instructorId){
		
		return new ResponseEntity<>(courseService.viewAllCourse(instructorId),HttpStatus.OK);
	}
	
=======
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<CourseRequestDTO>> viewAllCourse() {
        logger.info("Entering viewAllCourse method");
        ResponseEntity<List<CourseRequestDTO>> response = new ResponseEntity<>(courseService.viewAllCourse(), HttpStatus.OK);
        logger.info("Exiting viewAllCourse method with response: {}", response);
        return response;
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseRequestDTO>> viewAllCourse(@PathVariable int instructorId) {
        logger.info("Entering viewAllCourse method with instructorId: {}", instructorId);
        ResponseEntity<List<CourseRequestDTO>> response = new ResponseEntity<>(courseService.viewAllCourse(instructorId), HttpStatus.OK);
        logger.info("Exiting viewAllCourse method with response: {}", response);
        return response;
    }
>>>>>>> 642da552c8feab22e7f631e04c00c55e6690ca2f
}
