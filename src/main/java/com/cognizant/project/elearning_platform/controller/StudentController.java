package com.cognizant.project.elearning_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cognizant.project.elearning_platform.dto.CourseResponseDTO;
import com.cognizant.project.elearning_platform.dto.EnrollmentResponseDTO;
import com.cognizant.project.elearning_platform.dto.StudentResponseDTO;
import com.cognizant.project.elearning_platform.dto.SubmissionResponseDTO;
import com.cognizant.project.elearning_platform.dto.UpdateStudentDetailsDTO;
//import com.cognizant.project.elearning_platform.dto.UpdateStudentDetailsRequest;
import com.cognizant.project.elearning_platform.service.CourseService;
import com.cognizant.project.elearning_platform.service.EnrollmentService;
import com.cognizant.project.elearning_platform.service.StudentService;
import com.cognizant.project.elearning_platform.service.SubmissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    SubmissionService submissionService;

    @Autowired
    CourseService courseService;

    @PreAuthorize("#studentId == authentication.principal.id")
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<EnrollmentResponseDTO> enroll(@PathVariable int studentId, @PathVariable int courseId) {
        return new ResponseEntity<>(enrollmentService.enroll(studentId, courseId), HttpStatus.OK);
    }

    @PreAuthorize("#studentId == authentication.principal.id")
    @PostMapping("/{studentId}/submitAssessments/{assessmentId}")
    public ResponseEntity<SubmissionResponseDTO> submitAssessment(@PathVariable int studentId,
            @PathVariable int assessmentId) {
        return new ResponseEntity<>(submissionService.submitAssessment(studentId, assessmentId), HttpStatus.OK);
    }

    @PreAuthorize("#studentId == authentication.principal.id")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> viewStudent(@PathVariable int studentId) {
        return new ResponseEntity<>(studentService.viewStudent(studentId), HttpStatus.OK);
    }

    @PreAuthorize("#studentId == authentication.principal.id")
    @GetMapping("/{studentId}/enrolled-courses")
    public ResponseEntity<List<CourseResponseDTO>> viewEnrolled(@PathVariable int studentId) {
        return new ResponseEntity<>(enrollmentService.viewEnrolled(studentId), HttpStatus.OK);
    }

    @PreAuthorize("#studentId == authentication.principal.id")
    @PutMapping("/{studentId}/updateDetails")
    public ResponseEntity<StudentResponseDTO> updateStudentDetails(@PathVariable int studentId,
            @RequestBody @Valid UpdateStudentDetailsDTO request) {
        StudentResponseDTO updatedStudent = studentService.updateStudentDetails(studentId, request.getCollege(),
                request.getAge());
        return ResponseEntity.ok(updatedStudent);
    }
}
