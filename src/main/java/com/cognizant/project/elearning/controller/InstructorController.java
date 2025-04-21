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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

//    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;

    @Autowired
    AssessmentService assessmentService;

    @PreAuthorize("#instructorId==authentication.principal.id")
    @PostMapping("/{instructorId}/courses")
    public ResponseEntity<CourseResponseDTO> addCourse(@PathVariable int instructorId, @Valid @RequestBody CourseRequestDTO courseDTO) {
//        logger.info("Entering addCourse method with instructorId: {} and courseDTO: {}", instructorId, courseDTO);
        ResponseEntity<CourseResponseDTO> response = new ResponseEntity<>(courseService.addCourse(courseDTO, instructorId), HttpStatus.OK);
//        logger.info("Exiting addCourse method with response: {}", response);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @PostMapping("/{instructorId}/courses/{courseId}/assessments")
    public ResponseEntity<AssessmentResponseDTO> createAssessment(@Valid @RequestBody AssessmentRequestDTO assessmentRequestDTO,@PathVariable("instructorId") int instructorId, @PathVariable("courseId") int courseId){
        System.out.println("this is came in the controller");
    	
//    	logger.info("Entering createAssessment method with courseId: {}, and assessmentRequestDTO: {}", courseId, assessmentRequestDTO);
        ResponseEntity<AssessmentResponseDTO> response = new ResponseEntity<>(assessmentService.createAssessment(assessmentRequestDTO, courseId), HttpStatus.OK);
//        logger.info("Exiting createAssessment method with response: {}", response);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @PutMapping("{instructorId}/courses/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int instructorId, @PathVariable int courseId, @Valid @RequestBody CourseRequestDTO courseRequestDTO) {
//        logger.info("Entering updateCourse method with instructorId: {}, courseId: {}, and courseRequestDTO: {}", instructorId, courseId, courseRequestDTO);
        ResponseEntity<CourseResponseDTO> response = new ResponseEntity<>(courseService.updateCourse(instructorId, courseId, courseRequestDTO), HttpStatus.ACCEPTED);
//        logger.info("Exiting updateCourse method with response: {}", response);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @DeleteMapping("/{instructorId}")
    public ResponseEntity<String> removeInstructor(@PathVariable int instructorId) {
//        logger.info("Entering removeInstructor method with instructorId: {}", instructorId);
        ResponseEntity<String> response = new ResponseEntity<>(instructorService.removeInstructor(instructorId), HttpStatus.OK);
//        logger.info("Exiting removeInstructor method with response: {}", response);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @DeleteMapping("/{instructorId}/courses/{courseId}")
    public ResponseEntity<String> removeCourse(@PathVariable int courseId, @PathVariable int instructorId) {
//        logger.info("Entering removeCourse method with instructorId: {} and courseId: {}", instructorId, courseId);
        ResponseEntity<String> response = new ResponseEntity<>(courseService.removeCourse(courseId, instructorId), HttpStatus.OK);
//        logger.info("Exiting removeCourse method with response: {}", response);
        return response;
    }

    @PreAuthorize("#instructorId==authentication.principal.id")
    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorResponseDTO> viewInstructor(@PathVariable int instructorId) {
//        logger.info("Entering viewInstructor method with instructorId: {}", instructorId);
        ResponseEntity<InstructorResponseDTO> response = new ResponseEntity<>(instructorService.viewInstructor(instructorId), HttpStatus.OK);
//        logger.info("Exiting viewInstructor method with response: {}", response);
        return response;
    }
}
