package com.cognizant.project.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning.dto.AssessmentRequestDTO;
import com.cognizant.project.elearning.dto.AssessmentResponseDTO;
import com.cognizant.project.elearning.dto.CourseRequestDTO;
import com.cognizant.project.elearning.dto.CourseResponseDTO;
import com.cognizant.project.elearning.dto.InstructorResponseDTO;
import com.cognizant.project.elearning.service.AssessmentService;
import com.cognizant.project.elearning.service.CourseService;
import com.cognizant.project.elearning.service.InstructorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/instructors")
public class InstructorController {


    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;

    @Autowired
    AssessmentService assessmentService;

    @PreAuthorize("#instructorId==authentication.principal.id")
    @PostMapping("/{instructorId}/courses")
    public ResponseEntity<CourseResponseDTO> addCourse(@PathVariable int instructorId, @Valid @RequestBody CourseRequestDTO courseDTO) {
        ResponseEntity<CourseResponseDTO> response = new ResponseEntity<>(courseService.addCourse(courseDTO, instructorId), HttpStatus.OK);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @PostMapping("/{instructorId}/courses/{courseId}/assessments")
    public ResponseEntity<AssessmentResponseDTO> createAssessment(@Valid @RequestBody AssessmentRequestDTO assessmentRequestDTO,@PathVariable("instructorId") int instructorId, @PathVariable("courseId") int courseId){
        System.out.println("this is came in the controller");
    	
        ResponseEntity<AssessmentResponseDTO> response = new ResponseEntity<>(assessmentService.createAssessment(assessmentRequestDTO, courseId), HttpStatus.OK);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @PutMapping("{instructorId}/courses/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int instructorId, @PathVariable int courseId, @Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        ResponseEntity<CourseResponseDTO> response = new ResponseEntity<>(courseService.updateCourse(instructorId, courseId, courseRequestDTO), HttpStatus.ACCEPTED);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @DeleteMapping("/{instructorId}")
    public ResponseEntity<String> removeInstructor(@PathVariable int instructorId) {
        ResponseEntity<String> response = new ResponseEntity<>(instructorService.removeInstructor(instructorId), HttpStatus.OK);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @DeleteMapping("/{instructorId}/courses/{courseId}")
    public ResponseEntity<String> removeCourse(@PathVariable int courseId, @PathVariable int instructorId) {
        ResponseEntity<String> response = new ResponseEntity<>(courseService.removeCourse(courseId, instructorId), HttpStatus.OK);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorResponseDTO> viewInstructor(@PathVariable int instructorId) {
        ResponseEntity<InstructorResponseDTO> response = new ResponseEntity<>(instructorService.viewInstructor(instructorId), HttpStatus.OK);
        return response;
    }
}
