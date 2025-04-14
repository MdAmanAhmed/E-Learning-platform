package com.cognizant.project.elearning_platform.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.AssessmentRequestDTO;
import com.cognizant.project.elearning_platform.dto.AssessmentResponseDTO;
import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.repository.AssessmentRepository;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
@Service
public class AssessmentService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public AssessmentResponseDTO createAssessment(AssessmentRequestDTO assessmentRequestDTO,int courseId,
			int instructorId) {
		Assessment assessment=modelMapper.map(assessmentRequestDTO, Assessment.class);
Course course=courseRepository.findById(courseId).orElseThrow(
	()->new InvalidCourse("Course with Id "+courseId+" not found.")
		);
/*
		if(course.getInstructorId().getUserId()!=instructorId) {
			// throw custom exception like access denied
		}*/
		assessment.setCourseId(course);
		assessment=assessmentRepository.save(assessment);
AssessmentResponseDTO assessmentResponseDTO=modelMapper.map(assessment, AssessmentResponseDTO.class); 
assessmentResponseDTO.setCourseId(assessment.getCourseId().getCourseId());
assessmentResponseDTO.setTitle(assessment.getCourseId().getTitle());
assessmentResponseDTO.setContentURL(assessment.getCourseId().getContentURL());
assessmentResponseDTO.setInstructorId(assessment.getCourseId().getInstructorId().getUserId());
assessmentResponseDTO.setInstructorName(assessment.getCourseId().getInstructorId().getName());
		
		return assessmentResponseDTO;
	}
}
