package com.cognizant.project.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning.dto.CourseRequestDTO;
import com.cognizant.project.elearning.dto.CourseResponseDTO;
import com.cognizant.project.elearning.service.CourseService;



@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins="http://localhost:3000")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<CourseResponseDTO>> viewAllCourse() {

        ResponseEntity<List<CourseResponseDTO>> response = new ResponseEntity<>(courseService.viewAllCourse(), HttpStatus.OK);
        return response;
    }


}
